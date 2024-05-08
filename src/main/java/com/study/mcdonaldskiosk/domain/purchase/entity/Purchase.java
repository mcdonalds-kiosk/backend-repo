package com.study.mcdonaldskiosk.domain.purchase.entity;

import com.study.mcdonaldskiosk.domain.purchase.PurchaseStatus;
import jakarta.persistence.*;
import lombok.*;
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
  @Enumerated(EnumType.ORDINAL)
  @Column(name="status", nullable = false)
  private PurchaseStatus status;
  @Column(name = "payment_data", columnDefinition = "json")
  private String paymentData;
  @Column(name="created_at", nullable = false)
  private LocalDateTime createdAt = LocalDateTime.now();
  @Column(name="updated_at", nullable = false)
  private LocalDateTime updatedAt = LocalDateTime.now();
}