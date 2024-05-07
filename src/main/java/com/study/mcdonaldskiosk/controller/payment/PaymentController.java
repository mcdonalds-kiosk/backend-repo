package com.study.mcdonaldskiosk.controller.payment;

import com.study.mcdonaldskiosk.domain.order.Order;
import com.study.mcdonaldskiosk.domain.order.OrderRepository;
import com.study.mcdonaldskiosk.dto.payment.OrderSuccessResDto;
import com.study.mcdonaldskiosk.dto.payment.OrderSuccessReqDto;
import com.study.mcdonaldskiosk.dto.payment.PaymentReqDto;
import com.study.mcdonaldskiosk.dto.payment.PaymentResDto;
import com.study.mcdonaldskiosk.service.payment.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private PaymentService paymentService;
    private OrderRepository orderRepository;

    @PostMapping("/toss")
    public ResponseEntity<?> createTossPayment(@RequestBody PaymentReqDto paymentReqDto) {
        try {
            PaymentResDto resDto = paymentService.createPayment(paymentReqDto);
            return ResponseEntity.ok(resDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment");
        }
    }

    @PostMapping("/toss/success")
    public OrderSuccessResDto orderSuccess(@RequestBody OrderSuccessReqDto orderSuccessReqDto) {
        Order order = orderSuccessReqDto.toEntity();
        Order savedOrder = orderRepository.save(order);

        OrderSuccessResDto result = new OrderSuccessResDto();
        result.setStatus(200);
        result.setOrderIdx(savedOrder.getIdx());

        return result;
    }
}
