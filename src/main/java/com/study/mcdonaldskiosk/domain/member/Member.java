package com.study.mcdonaldskiosk.domain.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idx", nullable = false)
    private Integer idx;

    @Column(name = "id", nullable = false, length = 20)
    private String id;

    @Column(name = "pw", nullable = false, length = 20)
    private String pw;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "role", nullable = false)
    private Integer role;

    @Column(name = "join_date", nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime joinDate;

    @Builder
    public Member(String id, String pw, String email, String name, Integer role, LocalDateTime joinDate){
        this.id = id;
        this.pw = pw;
        this.email = email;
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
    }
}
