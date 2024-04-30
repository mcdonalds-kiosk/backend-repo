package com.study.mcdonaldskiosk.controller;

import com.study.mcdonaldskiosk.dto.MemberDto;
import com.study.mcdonaldskiosk.dto.ResMemberLoginDto;
import com.study.mcdonaldskiosk.entity.Member;
import com.study.mcdonaldskiosk.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberRepository memberRepository;
    @PostMapping("/join")
    public void join(@RequestBody MemberDto memberDto){
        Member member = memberDto.toEntity();
        memberRepository.save(member);
    }

    @GetMapping("/login")
    public ResMemberLoginDto login(@RequestParam String id, String pw){
        ResMemberLoginDto resMemberLoginDto = new ResMemberLoginDto();
        System.out.println(id+"와"+ pw);
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
