package com.study.mcdonaldskiosk.domain.member.dto;

import com.study.mcdonaldskiosk.domain.member.MemberRole;
import com.study.mcdonaldskiosk.domain.member.entity.Member;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
            .email(this.email).role(MemberRole.valueOf(this.role))
            .joinDate(LocalDateTime.now())
            .build();
    }
}