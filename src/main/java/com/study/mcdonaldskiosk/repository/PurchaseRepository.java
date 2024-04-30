package com.study.mcdonaldskiosk.repository;

import com.study.mcdonaldskiosk.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import javax.swing.text.html.Option;
=======
>>>>>>> 738850bfbb64d6f99cd0ff80501465dcc6346172
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
<<<<<<< HEAD
  Optional<Purchase> findByIdx(UUID orderId);
=======
  default Purchase findByIdx(String idx) {
    return findById(UUID.fromString(idx)).orElse(null);
  }
>>>>>>> 738850bfbb64d6f99cd0ff80501465dcc6346172
}