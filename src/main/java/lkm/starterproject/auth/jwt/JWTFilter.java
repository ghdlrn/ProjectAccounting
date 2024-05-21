package lkm.starterproject.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.auth.dto.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {
//http요청을 가로채 헤더에서 JWT엑세스 토큰 추출, 검증
    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authorization");   // 헤더에서 access키에 담긴 토큰을 꺼냄
        if (accessToken == null || !accessToken.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        accessToken = accessToken.substring(7);

        try {   // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    //response 상태코드 보내기
            return;
        }

        String category = jwtUtil.getCategory(accessToken); // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        if (!"access".equals(category)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 토큰 검증 완료되어 email, role 값을 획득
        String email = jwtUtil.getEmail(accessToken);
        String role = jwtUtil.getRole(accessToken);
        // MemberEntity에 이메일, 권한정보 셋
        Member member = new Member();
        member.setEmail(email);
        member.setRole(Role.valueOf(role));

        CustomUserDetails customUserDetails = new CustomUserDetails(member);
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

}