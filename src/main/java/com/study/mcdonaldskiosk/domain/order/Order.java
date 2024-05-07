package com.study.mcdonaldskiosk.domain.order;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private int idx;

    @Column(name = "menu_count", nullable = false)
    private int menuCount;

    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "member_idx", nullable = true)
    private Integer memberIdx;

    @Column(name = "purchase_idx", nullable = true)
    private UUID purchaseIdx; // UUID 타입을 사용하여 binary(16) 타입과 호환되도록 구현

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder
    public Order(int menuCount, int totalPrice, String status, Integer memberIdx, UUID purchaseIdx, LocalDateTime createdAt) {
        this.menuCount = menuCount;
        this.totalPrice = totalPrice;
        this.status = status;
        this.memberIdx = memberIdx;
        this.purchaseIdx = purchaseIdx;
        this.createdAt = createdAt;
    }
}
