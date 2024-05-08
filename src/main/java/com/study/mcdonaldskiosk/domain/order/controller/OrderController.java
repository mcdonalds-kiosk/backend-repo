package com.study.mcdonaldskiosk.domain.order.controller;

import com.study.mcdonaldskiosk.domain.order.repository.OrderRepository;
import com.study.mcdonaldskiosk.domain.order.dto.OrderSuccessReqDto;
import com.study.mcdonaldskiosk.domain.order.dto.OrderSuccessResDto;
import com.study.mcdonaldskiosk.domain.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
  private final OrderRepository orderRepository;

  @PostMapping("/toss/success/order")
  public OrderSuccessResDto orderSuccess(@RequestBody OrderSuccessReqDto orderSuccessReqDto) {
    Order order = orderSuccessReqDto.toEntity();
    orderRepository.save(order);

    OrderSuccessResDto result = new OrderSuccessResDto();
    result.setStatus(200);
    result.setOrderIdx(order.getIdx());

    return result;
  }
}
