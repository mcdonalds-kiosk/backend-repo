package com.study.mcdonaldskiosk.entity;

import com.study.mcdonaldskiosk.constant.MemberRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //Entity e = new Entity()
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private int idx;
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "pw", nullable = false)
    private String pw;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "role", nullable = false)
    private MemberRole role;
    @Column(name = "join_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate joinDate;

    @Builder
    public Member(String id, String pw, String email, String name, MemberRole role, LocalDate joinDate){
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
    }
}