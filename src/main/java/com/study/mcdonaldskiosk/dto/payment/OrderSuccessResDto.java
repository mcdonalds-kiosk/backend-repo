package com.study.mcdonaldskiosk.dto.payment;

import lombok.Data;

@Data
public class OrderSuccessResDto {
    private int status;
    private int orderIdx;
}
