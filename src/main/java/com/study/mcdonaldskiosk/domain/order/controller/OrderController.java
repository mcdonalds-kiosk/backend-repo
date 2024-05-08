package com.study.mcdonaldskiosk.domain.order.controller;

import com.study.mcdonaldskiosk.domain.order.repository.OrderRepository;
import com.study.mcdonaldskiosk.domain.order.dto.OrderSuccessReqDto;
import com.study.mcdonaldskiosk.domain.order.dto.OrderSuccessResDto;
import com.study.mcdonaldskiosk.domain.order.entity.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderController {
  private OrderRepository orderRepository;

  @PostMapping("/toss/success/order")
  public OrderSuccessResDto orderSuccess(@RequestBody OrderSuccessReqDto orderSuccessReqDto) {
    Order order = orderSuccessReqDto.toEntity();
    Order savedOrder = orderRepository.save(order);

    OrderSuccessResDto result = new OrderSuccessResDto();
    result.setStatus(200);
    result.setOrderIdx(savedOrder.getIdx());

    return result;
  }
}
