package lkm.starterproject.controller;

import lkm.starterproject.dto.MemberDto;
import lkm.starterproject.service.SignService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/signup")
    public String SignUp(MemberDto memberDto) {     //회원가입
        signService.signUp(memberDto);      //SignService에서 저장받은 회원정보(memberDto) 저장
        return "ok";
    }
}
