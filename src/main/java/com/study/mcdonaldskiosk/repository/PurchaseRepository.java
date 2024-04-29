package com.study.mcdonaldskiosk.repository;

import com.study.mcdonaldskiosk.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
  default Purchase findByIdx(String idx) {
    return findById(UUID.fromString(idx)).orElse(null);
  }
}