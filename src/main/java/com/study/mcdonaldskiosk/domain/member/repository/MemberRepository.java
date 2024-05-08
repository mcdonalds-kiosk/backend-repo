package com.study.mcdonaldskiosk.domain.member.repository;

import com.study.mcdonaldskiosk.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findTopById(String id);
    Optional<Member> findTopByIdAndPw(String id, String pw);
}
