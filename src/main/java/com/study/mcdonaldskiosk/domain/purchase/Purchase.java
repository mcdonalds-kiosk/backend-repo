package com.study.mcdonaldskiosk.domain.purchase;

import com.study.mcdonaldskiosk.domain.payment.PayType;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", updatable = false, nullable = false)
    private UUID idx;

    @Column(name = "member_idx")
    private Integer memberIdx;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_type", nullable = false, length = 20)
    private PayType payType;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "payment_key", length = 100)
    private String paymentKey;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "point")
    private Integer point;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "payment_data", columnDefinition = "JSON")
    private String paymentData;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
