package com.study.mcdonaldskiosk.domain.menu.service;

import com.study.mcdonaldskiosk.domain.menu.entity.Menu;
import com.study.mcdonaldskiosk.domain.menu.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    @Transactional(readOnly = true)
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Menu> findTopByIdx(Long menuIdx) {
        return menuRepository.findTopByIdx(menuIdx);
    }

    @Transactional
    public Menu updateMenuInfo(Long idx, Menu menuDetails) {
        Menu menu = menuRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("Menu Not Found"));
        menu.setName(menuDetails.getName());
        menu.setImageUrl(menuDetails.getImageUrl());
        menu.setPrice(menuDetails.getPrice());
        menu.setCategoryIdx(menuDetails.getCategoryIdx());
        menu.setUpdatedAt(LocalDateTime.now());
        return menuRepository.save(menu);
    }

    @Transactional
    public void deleteMenu(Long idx) {
        // Check if the member exists before attempting to delete
        if (menuRepository.existsById(idx)) {
            menuRepository.deleteById(idx);
        } else {
            throw new RuntimeException("Member not found with id: " + idx);
        }
    }
}
