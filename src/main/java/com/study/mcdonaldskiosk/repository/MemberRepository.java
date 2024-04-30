package com.study.mcdonaldskiosk.repository;

import com.study.mcdonaldskiosk.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findTopByIdAndPw(String id, String pw);
}
