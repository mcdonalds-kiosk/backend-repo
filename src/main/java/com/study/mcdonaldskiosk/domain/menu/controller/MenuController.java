package com.study.mcdonaldskiosk.domain.menu.controller;

import com.study.mcdonaldskiosk.domain.menu.dto.MenuResDto;
import com.study.mcdonaldskiosk.domain.menu.repository.MenuRepository;
import com.study.mcdonaldskiosk.domain.menu.entity.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {
  private final MenuRepository menuRepository;

  @GetMapping("")
  public List<MenuResDto> MenuAll(){
    List<Menu> menu = menuRepository.findAll();
    return menu.stream().map(MenuResDto::new).collect(Collectors.toList());
  }
}
