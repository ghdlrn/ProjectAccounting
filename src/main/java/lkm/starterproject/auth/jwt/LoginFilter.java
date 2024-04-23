package lkm.starterproject.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lkm.starterproject.auth.entity.RefreshEntity;
import lkm.starterproject.auth.repository.RefreshRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.*;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private RefreshRepository refreshRepository;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override       //인증메서드
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        //클라이언트요청에서 email, password 추출
        String email = obtainEmail(req);
        String password = obtainPassword(req);
        //email, password, role을 토큰에 담음
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);
        //토큰을 AuthenticationManger로 전달
        return authenticationManager.authenticate(authToken);
    }

    protected String obtainEmail(HttpServletRequest request) {  //username이 아닌 email로 검증하기 위해 정의
        return request.getParameter("email");
    }

    @Override       //인증성공했을시
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authentication) throws IOException {

        String email = authentication.getName(); //유저 정보

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        String access = jwtUtil.createJwt("access", email, role, 600000L);       //access토큰 생성 10분뒤 소멸
        String refresh = jwtUtil.createJwt("refresh", email, role, 86400000L);       //refresh토큰 생성 24시간 뒤 소멸
        addRefreshEntity(email, refresh, 86400000L);    //Refresh 토큰 저장

        // JSON 응답 생성
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> tokenInfo = new HashMap<>();
        tokenInfo.put("email", email);
        tokenInfo.put("role", role);
        tokenInfo.put("access_token", access);
        tokenInfo.put("refresh_token", refresh);

        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.getWriter().write(objectMapper.writeValueAsString(tokenInfo));

        res.setStatus(HttpStatus.OK.value());
    }

    @Override       //인증 실패시
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) {
        res.setStatus(401);
    }

    private void addRefreshEntity(String email, String refresh, Long expiredMs) {
        //email, refresh토큰, 만료일자를 새로 초기화해 refreshRepository에 해당값 저장
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setEmail(email);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) { //value : JWT값

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);     //쿠키 24시간뒤 소멸
        //cookie.setSecure(true);           //https 통신시 사용
        //cookie.setPath("/");          //쿠키적용 범위
        cookie.setHttpOnly(true);       //클라이언트에서 자바스크립트에 쿠키 접근하지 못하도록 설정

        return cookie;
    }
}