package com.study.mcdonaldskiosk.domain.order.repository;

import com.study.mcdonaldskiosk.domain.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findTopByIdx(int idx);
}
