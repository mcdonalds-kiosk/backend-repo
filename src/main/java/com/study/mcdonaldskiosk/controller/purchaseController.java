package com.study.mcdonaldskiosk.controller;

import com.study.mcdonaldskiosk.TossPaymentConfig;
import com.study.mcdonaldskiosk.dto.PurchaseDto;
import com.study.mcdonaldskiosk.dto.PurchaseFailDto;
import com.study.mcdonaldskiosk.dto.PurchaseResDto;
import com.study.mcdonaldskiosk.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class purchaseController {
  private final PurchaseService purchaseService;
  private final TossPaymentConfig tossPaymentConfig;

  public purchaseController(PurchaseService purchaseService, TossPaymentConfig tossPaymentConfig) {
    this.purchaseService = purchaseService;
    this.tossPaymentConfig = tossPaymentConfig;
  }

  @PostMapping("/toss")
  public ResponseEntity requestTossPayment(@RequestBody PurchaseDto purchaseReqDto) {
    PurchaseResDto purchaseResDto = purchaseService.requestTossPayment(purchaseReqDto.toEntity()).toPurchaseResDto();
    purchaseResDto.setSuccessUrl(purchaseResDto.getSuccessUrl() == null ? tossPaymentConfig.getSuccessUrl() : purchaseReqDto.getSuccessUrl());
    purchaseResDto.setFailUrl(purchaseResDto.getFailUrl() == null ? tossPaymentConfig.getFailUrl() : purchaseReqDto.getFailUrl());
    return ResponseEntity.ok().body(purchaseResDto);
  }

  @PostMapping("/toss/success")
  public ResponseEntity tossPaymentSuccess(@RequestParam String paymentKey, @RequestParam String orderId, @RequestParam Long amount) {

    return ResponseEntity.ok().body(purchaseService.tossPaymentSuccess(paymentKey, orderId, amount));
  }

  @PostMapping("/toss/fail")
  public ResponseEntity tossPaymentFail(@RequestParam String code, @RequestParam String message, @RequestParam String orderId) {
    purchaseService.tossPaymentFail(code, message, orderId);
    return ResponseEntity.ok().body(PurchaseFailDto.builder().errorCode(code).errorMessage(message).orderId(orderId).build());
  }
}
