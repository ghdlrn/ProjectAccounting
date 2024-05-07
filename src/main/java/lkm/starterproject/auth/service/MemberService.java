package lkm.starterproject.auth.service;

import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.auth.repository.MemberRepository;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.dto.MemberDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void SignUp(MemberDto memberDto) {
        String email = memberDto.getEmail();
        String password = memberDto.getPassword();
        String username = memberDto.getUsername();

        Boolean isMemberExist = memberRepository.existsByEmail(email);      //중복되는 email값 있는지 확인

        if (isMemberExist) {

            return;
        }
        Member member = new Member().builder()    // isMemberExist로 동일한 email이 없으면 해당 유저정보 저장
                .username(username)
                .password(passwordEncoder.encode(password))     //비밀번호 암호화해서 저장
                .email(email)
                .role(Role.USER)    //회원가입대상자는 모두 권한 USER
                .build();
        memberRepository.save(member);
    }
}
