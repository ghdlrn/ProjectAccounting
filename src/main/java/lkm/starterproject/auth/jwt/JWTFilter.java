package lkm.starterproject.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.entity.MemberEntity;
import lkm.starterproject.auth.dto.CustomUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String accessToken = request.getHeader("access");   // 헤더에서 access키에 담긴 토큰을 꺼냄

        if (accessToken == null) {  // 토큰이 없다면 다음 필터로 넘김
            filterChain.doFilter(request, response);
            return;
        }

        try {   // 토큰 만료 여부 확인, 만료시 다음 필터로 넘기지 않음
            jwtUtil.isExpired(accessToken);
        } catch (ExpiredJwtException e) {

            PrintWriter writer = response.getWriter();    //response
            writer.print("access token expired");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    //response 상태코드 보내기
            return;
        }

        String category = jwtUtil.getCategory(accessToken); // 토큰이 access인지 확인 (발급시 페이로드에 명시)
        if (!category.equals("access")) {       //access토큰이 아니면 해당 상태코드 발생

            PrintWriter writer = response.getWriter();  //response
            writer.print("invalid access token");

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);    //response 상태코드 보내기
            return;
        }
        // 토큰 검증 완료되어 email, role 값을 획득
        String email = jwtUtil.getEmail(accessToken);
        String role = jwtUtil.getRole(accessToken);
        // MemberEntity에 이메일, 권한정보 셋
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(email);
        memberEntity.setRole(Role.valueOf(role));

        CustomUserDetails customUserDetails = new CustomUserDetails(memberEntity);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);    //SecurityContextHolder에 유저정보를 담아 일시적인 세션 생성

        filterChain.doFilter(request, response);
    }
}