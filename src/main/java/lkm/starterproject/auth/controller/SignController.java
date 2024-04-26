package lkm.starterproject.auth.controller;

import lkm.starterproject.auth.service.SignService;
import lkm.starterproject.auth.dto.MemberDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class SignController {

    private final SignService signService;

    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/signup")
    public String SignUp(@RequestBody MemberDto memberDto) {     //회원가입
        signService.SignUp(memberDto);      //SignService에서 저장받은 회원정보(memberDto) 저장
        return "redirect:/auth/login";
    }
}
