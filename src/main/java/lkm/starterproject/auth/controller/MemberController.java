package lkm.starterproject.auth.controller;

import lkm.starterproject.auth.service.MemberService;
import lkm.starterproject.auth.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public String SignUp(@RequestBody MemberDto memberDto) {     //회원가입
        memberService.SignUp(memberDto);      //SignService에서 저장받은 회원정보(memberDto) 저장
        return "sign up";
    }
}
