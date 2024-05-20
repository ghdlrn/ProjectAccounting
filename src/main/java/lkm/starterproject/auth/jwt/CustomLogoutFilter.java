package lkm.starterproject.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lkm.starterproject.auth.repository.RefreshRepository;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

public final class CustomLogoutFilter extends GenericFilterBean {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public CustomLogoutFilter(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doFilter((HttpServletRequest) request, (HttpServletResponse) response, chain);
    }

    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String requestUri = request.getRequestURI();    //url값 저장
        if (!requestUri.matches("^\\/logout$")) {   //로그아웃경로가 아니면 다음필터로
            filterChain.doFilter(request, response);
            return;
        }
        String requestMethod = request.getMethod();
        if (!requestMethod.equals("POST")) {        //로그아웃 경로라도 POST요청이 아니면 다음 필터로
            filterChain.doFilter(request, response);
            return;
        }
        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }
        if (refresh == null) {  //refresh토큰이 없으면 해당 상태메시지 보냄
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            jwtUtil.isExpired(refresh); //refresh토큰이 만료되었는지 확인(만료되었으면 이미 로그아웃되어있단 뜻)
        } catch (ExpiredJwtException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String category = jwtUtil.getCategory(refresh); //토큰이 refresh인지, access인지 확인
        if (!category.equals("refresh")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); //refresh토큰이 아니면 상태코드보냄
            return;
        }

        Boolean isExist = refreshRepository.existsByRefresh(refresh);   //DB에 해당토큰이 존재하는지 확인
        if (!isExist) {     //DB에 토큰이 없으면 상태코드 보냄
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //로그아웃 진행-------------------------------------------------------------------------------------------
        refreshRepository.deleteByRefresh(refresh);  //Refresh 토큰 DB에서 제거
        Cookie cookie = new Cookie("refresh", null);    //Refresh 토큰 Cookie 값 0으로 생성
        cookie.setMaxAge(0);        //쿠키 시간 0
        cookie.setPath("/");          //전역경로
        response.addCookie(cookie);     //쿠키 추가
        response.setStatus(HttpServletResponse.SC_OK);      //쿠키 생성
    }
}