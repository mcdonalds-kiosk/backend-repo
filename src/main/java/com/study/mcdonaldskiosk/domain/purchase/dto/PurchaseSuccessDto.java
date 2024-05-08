package com.study.mcdonaldskiosk.domain.purchase.dto;

import lombok.Data;

@Data
public class PurchaseSuccessDto {
  String mid;
  String version;
  String paymentKey;
  String status;
  String orderId;
  String orderName;
  String requestedAt;
  String approvedAt;
  String useEscrow; // false
  String cultureExpense; // false
  PurchaseSuccessCardDto card;
  String type;
  String currency; // "KRW"
  String totalAmount;
  String balanceAmount;
  String suppliedAmount;
  String vat;
  String method;
}
