package com.study.mcdonaldskiosk.controller.payment;

import com.study.mcdonaldskiosk.dto.payment.PaymentReqDto;
import com.study.mcdonaldskiosk.dto.payment.PaymentResDto;
import com.study.mcdonaldskiosk.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/toss")
    public ResponseEntity<?> createTossPayment(@RequestBody PaymentReqDto paymentReqDto) {

        // fetch 로 불러서 orderID를 불러와서 orderId를 불러줬다.
        try {
            PaymentResDto resDto = paymentService.createPayment(paymentReqDto);
            return ResponseEntity.ok(resDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing payment");
        }
    }


}
