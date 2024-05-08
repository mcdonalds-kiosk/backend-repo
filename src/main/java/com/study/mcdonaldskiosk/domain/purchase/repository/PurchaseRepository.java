package com.study.mcdonaldskiosk.domain.purchase.repository;

import com.study.mcdonaldskiosk.domain.purchase.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
  Optional<Purchase> findByIdx(UUID idx);
}