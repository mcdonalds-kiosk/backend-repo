package com.study.mcdonaldskiosk.dto;

import com.study.mcdonaldskiosk.entity.Purchase;
import lombok.*;
<<<<<<< HEAD
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
=======
>>>>>>> 738850bfbb64d6f99cd0ff80501465dcc6346172

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseDto {
<<<<<<< HEAD
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
=======
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
>>>>>>> 738850bfbb64d6f99cd0ff80501465dcc6346172
        .build();
  }
}
