package com.study.mcdonaldskiosk.domain.order.dto;

import com.study.mcdonaldskiosk.domain.order.entity.Order;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class OrderSuccessReqDto {
    int menuCount;
    int totalPrice;
    String status;
    int memberIdx;
    String purchaseIdx;

    public Order toEntity() {
        return Order.builder()
                .menuCount(menuCount)
                .totalPrice(totalPrice)
                .status(status)
                .memberIdx(memberIdx)
                .purchaseIdx(UUID.fromString(purchaseIdx))
                .createdAt(LocalDateTime.now())
                .build();
    }
}