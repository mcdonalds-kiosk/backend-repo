package com.study.mcdonaldskiosk.service.payment;

import com.study.mcdonaldskiosk.domain.purchase.Purchase;
import com.study.mcdonaldskiosk.domain.purchase.PurchaseRepository;
import com.study.mcdonaldskiosk.dto.payment.PaymentReqDto;
import com.study.mcdonaldskiosk.dto.payment.PaymentResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PurchaseRepository purchaseRepository;

    @Transactional
    public PaymentResDto createPayment(PaymentReqDto dto) {

        Purchase purchase = dto.toEntity();
        Purchase savedPurchase = purchaseRepository.save(purchase);

        return buildPaymentResponseDto(savedPurchase);
    }

    private PaymentResDto buildPaymentResponseDto(Purchase purchase) {
        return PaymentResDto.builder()
                .payType(purchase.getPayType().toString())
                .amount(purchase.getPrice())
                .orderName("테스트 결제")
                .orderId(purchase.getIdx().toString())
                .customerEmail("info@example.com")
                .customerName("테스트 계정")
//                .successUrl("http://example.com/success")
//                .failUrl("http://example.com/fail")
                .build();
    }
}
