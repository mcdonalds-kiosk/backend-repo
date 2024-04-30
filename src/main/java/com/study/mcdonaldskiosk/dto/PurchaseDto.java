package com.study.mcdonaldskiosk.dto;

import com.study.mcdonaldskiosk.entity.Purchase;
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
        .status(1)
        .createdAt(LocalDateTime.now())
        .updatedAt(LocalDateTime.now())
        .build();
  }
}
