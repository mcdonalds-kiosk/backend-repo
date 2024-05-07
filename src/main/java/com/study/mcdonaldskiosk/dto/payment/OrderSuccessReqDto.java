package com.study.mcdonaldskiosk.dto.payment;

import com.study.mcdonaldskiosk.domain.order.Order;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSuccessReqDto {
    int menuCount;
    int totalPrice;
    String status;
    int memberIdx;
    Long purchaseIdx;

    public Order toEntity() {
        return Order.builder()
                .menuCount(menuCount)
                .totalPrice(totalPrice)
                .status(status)
                .memberIdx(memberIdx)
                .createdAt(LocalDateTime.now())
                .build();
    }
}