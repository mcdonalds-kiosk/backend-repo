package com.study.mcdonaldskiosk.domain.menu;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idx", nullable = false)
    private Long idx;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="image_url", nullable = false)
    private String imageUrl;

    @Column(name="price", nullable = false)
    private Long price;

    @Column(name="category_idx", nullable = false)
    private int categoryIdx;

    @Column(name="updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Builder
    public Menu(String name, String imageUrl, Long price, int categoryIdx, LocalDateTime updatedAt) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.categoryIdx = categoryIdx;
        this.updatedAt = updatedAt;
    }
}