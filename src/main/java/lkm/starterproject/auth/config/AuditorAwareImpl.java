package lkm.starterproject.auth.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //로그인한 사용자 ID를 보유하는 Authentication 객체 검색
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = "";
        if (authentication != null) {   //null이 아니면 사용자 이름 검색
            userId = authentication.getName();  
        }
        return Optional.of(userId); //사용자 이름 반환, 인증된 사용자 없으면 null반환
    }
}
