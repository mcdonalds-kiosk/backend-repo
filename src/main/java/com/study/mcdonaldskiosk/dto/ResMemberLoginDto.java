package com.study.mcdonaldskiosk.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ResMemberLoginDto {
    private int idx;
    private Boolean status;
    private String name; // 세션에 저장될 멤버 이름
}
