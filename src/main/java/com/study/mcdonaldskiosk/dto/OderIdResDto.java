package com.study.mcdonaldskiosk.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OderIdResDto {
  String status;
  String orderId;
}