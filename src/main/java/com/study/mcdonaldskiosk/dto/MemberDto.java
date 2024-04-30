package com.study.mcdonaldskiosk.dto;


import com.study.mcdonaldskiosk.constant.MemberRole;
import com.study.mcdonaldskiosk.entity.Member;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberDto {
    private String id;
    private String pw;
    private String email;
    private String name;
    private String role;


    public Member toEntity(){
        return Member.builder().
                id(this.id).pw(this.pw).name(this.name)
                .email(this.email).role(MemberRole.fromValue(Integer.parseInt(this.role)))
                .joinDate(LocalDate.now())
                .build();
    }
}