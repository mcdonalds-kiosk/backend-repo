package com.study.mcdonaldskiosk.domain.member.controller;

import com.study.mcdonaldskiosk.domain.member.MemberRole;
import com.study.mcdonaldskiosk.domain.member.entity.Member;
import com.study.mcdonaldskiosk.domain.member.repository.MemberRepository;
import com.study.mcdonaldskiosk.domain.member.dto.MemberDto;
import com.study.mcdonaldskiosk.domain.member.dto.ResCheckMemberIdDupDto;
import com.study.mcdonaldskiosk.domain.member.dto.ResMemberLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberRepository memberRepository;
    @GetMapping("/check_dup_id")
    public ResCheckMemberIdDupDto check_dup_id(@RequestParam String id){
        ResCheckMemberIdDupDto resCheckMemberIdDupDto = new ResCheckMemberIdDupDto();
        Optional<Member> memberWithId = memberRepository.findTopById(id);
        if(memberWithId.isPresent()){
            resCheckMemberIdDupDto.setStatus(false);
        }
        else{
            resCheckMemberIdDupDto.setStatus(true);
        }
        return resCheckMemberIdDupDto;
    }

    @PostMapping("/join")
    public void join(@RequestBody MemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
    }

    @GetMapping("/login")
    public ResMemberLoginDto login(@RequestParam String id, String pw){
        ResMemberLoginDto resMemberLoginDto = new ResMemberLoginDto();
        resMemberLoginDto.setStatus(false);
        Optional<Member> member = memberRepository.findTopByIdAndPw(id, pw);
        if(member.isPresent()){
            if(member.get().getRole() != MemberRole.UNKNOWN) {
                resMemberLoginDto.setStatus(true);
                resMemberLoginDto.setIdx(member.get().getIdx());
                resMemberLoginDto.setName(member.get().getName());
            }
        }
        return resMemberLoginDto;
    }

}
