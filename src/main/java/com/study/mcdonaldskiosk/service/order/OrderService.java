package com.study.mcdonaldskiosk.service.order;

import com.study.mcdonaldskiosk.domain.order.Order;
import com.study.mcdonaldskiosk.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Order> findTopByIdx(int orderIdx) {
        return orderRepository.findTopByIdx(orderIdx);
    }

    @Transactional
    public Order updateOrderInfo(int idx, Order orderDetails) {
        Order order = orderRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("Order Not Found"));
        order.setIdx(orderDetails.getIdx());
        order.setMenuCount(orderDetails.getMenuCount());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setStatus(orderDetails.getStatus());
        order.setMemberIdx(orderDetails.getMemberIdx());
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(int idx) {
        // Check if the member exists before attempting to delete
        if (orderRepository.existsById(idx)) {
            orderRepository.deleteById(idx);
        } else {
            throw new RuntimeException("Member not found with id: " + idx);
        }
    }
}
