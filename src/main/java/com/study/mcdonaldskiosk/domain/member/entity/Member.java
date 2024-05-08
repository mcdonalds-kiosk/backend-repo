package com.study.mcdonaldskiosk.domain.member.entity;

import com.study.mcdonaldskiosk.domain.member.MemberRole;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
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
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private MemberRole role;
    @Column(name = "join_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime joinDate;

    @Builder
    public Member(String id, String pw, String email, String name, MemberRole role, LocalDateTime joinDate){
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
    }
}