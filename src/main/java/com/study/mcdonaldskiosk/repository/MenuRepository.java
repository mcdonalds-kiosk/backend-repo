package com.study.mcdonaldskiosk.repository;

import com.study.mcdonaldskiosk.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {
}