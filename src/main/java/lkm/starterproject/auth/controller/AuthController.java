package lkm.starterproject.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import lkm.starterproject.auth.service.MemberService;
import lkm.starterproject.auth.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    private final MemberService memberService;

    public AuthController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/auth/signup")
    public String SignUp(@RequestBody MemberDto memberDto) {     //회원가입
        memberService.SignUp(memberDto);      //SignService에서 저장받은 회원정보(memberDto) 저장
        return "sign up";
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        String accessToken = token.substring(7);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
