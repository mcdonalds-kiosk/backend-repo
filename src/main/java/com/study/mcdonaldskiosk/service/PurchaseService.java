package com.study.mcdonaldskiosk.service;

import com.study.mcdonaldskiosk.TossPaymentConfig;
import com.study.mcdonaldskiosk.dto.PurchaseSuccessDto;
import com.study.mcdonaldskiosk.entity.Purchase;
import com.study.mcdonaldskiosk.repository.PurchaseRepository;

import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;


@Service
public class PurchaseService {
  private final PurchaseRepository purchaseRepository;
  private final TossPaymentConfig tossPaymentConfig;

  public PurchaseService(PurchaseRepository purchaseRepository, TossPaymentConfig tossPaymentConfig, TossPaymentConfig tossPaymentConfig1) {
    this.purchaseRepository = purchaseRepository;
    this.tossPaymentConfig = tossPaymentConfig1;
  }

  public Purchase requestTossPayment(Purchase purchase){
    return purchaseRepository.save(purchase);
  }


  @Transactional
  public PurchaseSuccessDto tossPaymentSuccess(String paymentKey, String orderId, Long amount){
    Purchase purchase = verifyPayment(orderId, amount);
    PurchaseSuccessDto result = requestPaymentAccept(paymentKey, orderId, amount);
    System.out.println(result.toString());
    purchase.setPaymentKey(paymentKey);
    purchase.setStatus(1);
    purchase.setPoint(amount*0.01);
    return result;
  }

  @Transactional
  public PurchaseSuccessDto requestPaymentAccept(String paymentKey, String orderId, Long amount){
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = getHeaders();
    JSONObject params = new JSONObject();
    params.put("orderId", orderId);
    params.put("amount", amount);

    PurchaseSuccessDto result = null;
    try {
      result = restTemplate.postForObject(TossPaymentConfig.URL + paymentKey, new HttpEntity<>(params, headers), PurchaseSuccessDto.class);
    } catch (Exception e){
      throw new RuntimeException("Failed to process payment: " + e.getMessage());
    }

    return result;
  }

  private HttpHeaders getHeaders() {
     HttpHeaders headers = new HttpHeaders();
     String encodeAuthKey = new String(
         Base64.getEncoder().encode((tossPaymentConfig.getSecretKey() + ":").getBytes(StandardCharsets.UTF_8))
     );

     headers.setBasicAuth(encodeAuthKey);
     headers.setContentType(MediaType.APPLICATION_JSON);
     headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
     return headers;
  }

  public Purchase verifyPayment(String orderId, Long amount) {
    Purchase purchase = purchaseRepository.findByIdx(orderId);
    if (purchase == null) {
      throw new RuntimeException("Payment not found for order ID: " + orderId);
    }
    if (!purchase.getAmount().equals(amount)) {
      throw new RuntimeException("Invalid payment amount for order ID: " + orderId);
    }
    return purchase;
  }

  @Transactional
  public void tossPaymentFail(String code, String message, String orderId) {
    Purchase purchase = purchaseRepository.findByIdx(orderId);
    if (purchase == null) {
      throw new RuntimeException("Purchase not found for order ID: " + orderId);
    }
    purchase.setStatus(0);
  }




}
