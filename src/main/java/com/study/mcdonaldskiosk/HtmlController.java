package com.study.mcdonaldskiosk;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HtmlController {
    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
