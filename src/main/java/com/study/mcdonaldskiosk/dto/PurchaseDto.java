package com.study.mcdonaldskiosk.dto;

import com.study.mcdonaldskiosk.entity.Purchase;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
  private String payType;
  private Long amount;
  private String orderId;
  private String successUrl;
  private String failUrl;

  public Purchase toEntity() {
    return Purchase.builder()
        .payType(payType)
        .amount(amount)
        .status(0)
        .build();
  }
}
