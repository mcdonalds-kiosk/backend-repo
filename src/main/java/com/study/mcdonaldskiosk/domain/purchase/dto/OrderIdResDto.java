package com.study.mcdonaldskiosk.domain.purchase.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderIdResDto {
  String status;
  String orderId;
}
