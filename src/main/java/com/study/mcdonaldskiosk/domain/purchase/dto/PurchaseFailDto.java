package com.study.mcdonaldskiosk.domain.purchase.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseFailDto {
  String errorCode;
  String errorMessage;
  String orderId;
}
