package com.study.mcdonaldskiosk.domain.menu.repository;

import com.study.mcdonaldskiosk.domain.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findTopByIdx(Long idx);
}
