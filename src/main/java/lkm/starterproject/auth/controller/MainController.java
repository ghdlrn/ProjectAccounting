package lkm.starterproject.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;

@RestController
public class MainController {

    @GetMapping("/")
    public String main() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();    //SecurityContextHolder에서 인증된 사용자의 이메일을 검색

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); //SecurityContextHolder에서 자격증명 및 권한 검색

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();      //권한 검색하여 객체에서 권한 이름을 추출

        return "User Controller" + email + role;
    }
}
