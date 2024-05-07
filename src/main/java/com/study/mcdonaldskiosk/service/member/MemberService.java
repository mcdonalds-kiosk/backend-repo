package com.study.mcdonaldskiosk.service.member;

import com.study.mcdonaldskiosk.domain.member.Member;
import com.study.mcdonaldskiosk.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Member> findTopByIdx(int memberIdx) {
        return memberRepository.findTopByIdx(memberIdx);
    }

    @Transactional
    public Member updateMember(int idx, Member memberDetails) {
        Member member = memberRepository.findTopByIdx(idx).orElseThrow(() -> new RuntimeException("User Not Found"));
        member.setId(memberDetails.getId());
        member.setPw(memberDetails.getPw());
        member.setName(memberDetails.getName());
        member.setRole(memberDetails.getRole());
        member.setJoinDate(LocalDateTime.now());
        return memberRepository.save(member);
    }

    @Transactional
    public void deleteMember(int idx) {
        // Check if the member exists before attempting to delete
        if (memberRepository.existsById(idx)) {
            memberRepository.deleteById(idx);
        } else {
            throw new RuntimeException("Member not found with id: " + idx);
        }
    }
}
