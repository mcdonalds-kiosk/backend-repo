package com.study.mcdonaldskiosk.domain.menu.dto;

import com.study.mcdonaldskiosk.domain.menu.entity.Menu;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MenuResDto {
  private Long idx;
  private String name;
  private String imageUrl;
  private Long price;
  private int categoryIdx;
  private LocalDateTime updatedAt;

  public MenuResDto(Menu entity){
    this.idx = entity.getIdx();
    this.name = entity.getName();
    this.imageUrl = entity.getImageUrl();
    this.price = entity.getPrice();
    this.categoryIdx = entity.getCategoryIdx();
    this.updatedAt = entity.getUpdatedAt();
  }
}