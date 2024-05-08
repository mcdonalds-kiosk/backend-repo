package com.study.mcdonaldskiosk.dto.payment;

import com.study.mcdonaldskiosk.domain.payment.PayType;
import com.study.mcdonaldskiosk.domain.purchase.Purchase;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentReqDto {
    // 토스 페이먼츠 결제창 띄우기 위해 필요한 파라미터들
    private PayType payType;
    private int price;
    private int amount;

    private String orderName;

    public Purchase toEntity() {
        return Purchase.builder()
                .memberIdx(1)
                .payType(payType)
                .price(price)
                .amount(amount)
                .status(1)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}

