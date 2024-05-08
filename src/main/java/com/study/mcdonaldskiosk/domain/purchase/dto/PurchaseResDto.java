package com.study.mcdonaldskiosk.domain.purchase.dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResDto {
  private String payType;
  private Long amount;
  private String orderId;
  private String orderName;
  private String customerEmail;
  private String customerName;
  private String successUrl;
  private String failUrl;
  private String paySuccessYn;
  private String createdAt;
}
