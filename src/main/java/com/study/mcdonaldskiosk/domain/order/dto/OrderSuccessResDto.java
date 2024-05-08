package com.study.mcdonaldskiosk.domain.order.dto;

import lombok.Data;

@Data
public class OrderSuccessResDto {
    private int status;
    private int orderIdx;
}
