package com.study.mcdonaldskiosk.domain.menu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "menu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

}