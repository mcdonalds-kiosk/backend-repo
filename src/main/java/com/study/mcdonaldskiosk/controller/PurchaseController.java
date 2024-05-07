package com.study.mcdonaldskiosk.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mcdonaldskiosk.TossPaymentConfig;
import com.study.mcdonaldskiosk.constant.PurchaseStatus;
import com.study.mcdonaldskiosk.dto.PurchaseDto;
import com.study.mcdonaldskiosk.dto.PurchaseFailDto;
import com.study.mcdonaldskiosk.dto.PurchaseSuccessDto;
import com.study.mcdonaldskiosk.dto.OderIdResDto;
import com.study.mcdonaldskiosk.entity.Purchase;
import com.study.mcdonaldskiosk.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseController {
  private final PurchaseRepository purchaseRepository;
  private final TossPaymentConfig tossPaymentConfig;

  @PostMapping("/toss")
  public OderIdResDto requestTossPayment(@RequestBody PurchaseDto purchaseReqDto) {
    Purchase purchase = purchaseReqDto.toEntity();
    purchaseRepository.save(purchase);

    String orderId = String.valueOf(purchase.getIdx());
    return OderIdResDto.builder()
        .status("ok").orderId(orderId)
        .build();
  }

  @Transactional
  @GetMapping("/toss/success")
  public PurchaseSuccessDto tossPaymentSuccess(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount) throws JsonProcessingException {
    Purchase purchase = purchaseRepository.findByIdx(UUID.fromString(orderId)).orElseThrow(() -> {
      throw new RuntimeException("orderId notFound");
    });
    if(!purchase.getPrice().equals(amount)){
      throw new RuntimeException("price notFound");
    }

    String secretKey = tossPaymentConfig.getSecretKey() + ":";

    // rest api 요청을 해줄 수 있는 내장 클래스
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    String encodeAuthKey = new String(Base64.getEncoder().encode(secretKey.getBytes(StandardCharsets.UTF_8)));
    headers.setBasicAuth(encodeAuthKey);
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

    JSONObject params = new JSONObject();
    params.put("orderId", orderId);
    params.put("amount", amount);

    PurchaseSuccessDto result;
    try {
      result = restTemplate.postForEntity(TossPaymentConfig.URL + paymentKey,
          new HttpEntity<>(params, headers),
          PurchaseSuccessDto.class).getBody();
    }catch(Exception e){
      throw new RuntimeException("already approved");
    }

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonResult = objectMapper.writeValueAsString(result);

    System.out.println(paymentKey);
    purchase.setPaymentKey(paymentKey);
    purchase.setStatus(PurchaseStatus.valueOf("DONE"));
    purchase.setPaymentData(jsonResult);
    return result;
  }

  @GetMapping("/toss/fail")
  public PurchaseFailDto tossPaymentFail(@RequestParam String code, @RequestParam String message, @RequestParam String orderId) {
    Purchase purchase = purchaseRepository.findByIdx(UUID.fromString(orderId)).orElseThrow(() -> {
      throw new RuntimeException("payment not found");
    });
    purchase.setStatus(PurchaseStatus.valueOf("ABORTED"));
    return PurchaseFailDto.builder()
        .errorCode(code)
        .errorMessage(message)
        .orderId(orderId)
        .build();
  }
}
