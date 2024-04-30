package com.study.mcdonaldskiosk.entity;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.*;
=======
import com.study.mcdonaldskiosk.dto.PurchaseResDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
>>>>>>> 738850bfbb64d6f99cd0ff80501465dcc6346172

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Purchase {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name="idx", nullable=false)
  private UUID idx;
  @Column(name="member_idx", nullable = false)
  private int memberIdx;
  @Column(name="pay_type", nullable = false)
  private String payType;
  @Column(name="price", nullable = false)
  private Long price;
  @Column(name="payment_key")
  private String paymentKey;
  @Column(name="amount", nullable = false)
  private Long amount;
  @Column(name="point")
  private double point;
  @Column(name="status", nullable = false)
  private int status;
  @Column(name = "payment_data", columnDefinition = "json")
  private String paymentData;
  @Column(name="created_at", nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();
  @Column(name="updated_at", nullable = false)
  private LocalDateTime updatedAt = LocalDateTime.now();

<<<<<<< HEAD
=======
  public PurchaseResDto toPurchaseResDto() {
    return PurchaseResDto.builder()
        .payType(payType)
        .amount(amount)
        .createdAt(String.valueOf(getCreatedAt()))
        .build();
  }
>>>>>>> 738850bfbb64d6f99cd0ff80501465dcc6346172
}
