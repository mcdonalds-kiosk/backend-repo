package com.study.mcdonaldskiosk.controller;

import com.study.mcdonaldskiosk.dto.MemberDto;
import com.study.mcdonaldskiosk.dto.ResCheckMemberIdDupDto;
import com.study.mcdonaldskiosk.dto.ResMemberLoginDto;
import com.study.mcdonaldskiosk.entity.Member;
import com.study.mcdonaldskiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
            resMemberLoginDto.setStatus(true);
            resMemberLoginDto.setIdx(member.get().getIdx());
            resMemberLoginDto.setName(member.get().getName());
        }
        return resMemberLoginDto;
    }

}
