package com.study.mcdonaldskiosk.domain.purchase.dto;

import com.study.mcdonaldskiosk.domain.purchase.entity.Purchase;
import com.study.mcdonaldskiosk.domain.purchase.PurchaseStatus;
import lombok.*;
import java.time.LocalDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
  private int memberIdx;
  private String payType;
  private Long amount;
  public Purchase toEntity() {
    return Purchase.builder()
        .memberIdx(memberIdx)
        .payType(payType)
        .price(amount)
        .amount(amount)
        .status(PurchaseStatus.valueOf("IN_PROGRESS"))
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}