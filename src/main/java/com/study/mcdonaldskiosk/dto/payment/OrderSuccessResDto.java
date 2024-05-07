package com.study.mcdonaldskiosk.dto.payment;

import lombok.Data;

@Data
public class OrderSuccessResDto {
    private Integer status;
    private Integer orderIdx;
}
