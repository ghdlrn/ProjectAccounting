package lkm.starterproject.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lkm.starterproject.auth.entity.Refresh;
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
    private final RefreshRepository refreshRepository;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // JSON 데이터에서 사용자 정보 파싱
            UserLoginCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginCredentials.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credentials.getEmail(),
                    credentials.getPassword()
            );
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        String access = jwtUtil.createJwt("access", email, role, 86400000L);    //access토큰 1일 뒤 소멸
        String refresh = jwtUtil.createJwt("refresh", email, role, 1209600000L);       //refresh토큰 생성 2주 뒤 소멸
        addRefreshEntity(email, refresh, 86400000L);    //Refresh 토큰 저장

        setCookie(res, "access", access, 86400, true, false); // 개발 환경에서는 secure를 false로 설정
        setCookie(res, "refresh", refresh, 86400, true, false);
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

        Refresh refreshEntity = new Refresh();
        refreshEntity.setEmail(email);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }

    private void setCookie(HttpServletResponse response, String name, String value, int maxAge, boolean httpOnly, boolean secure) {
        StringBuilder cookieBuilder = new StringBuilder();
        cookieBuilder.append(name).append("=").append(value).append(";");
        cookieBuilder.append("Max-Age=").append(maxAge).append(";");
        cookieBuilder.append("Path=/;");
        if (httpOnly) {
            cookieBuilder.append("HttpOnly;");
        }
        if (secure) {
            cookieBuilder.append("Secure;");
        }
        cookieBuilder.append("SameSite=Strict;");
        response.addHeader("Set-Cookie", cookieBuilder.toString());
    }

}