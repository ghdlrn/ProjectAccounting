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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

public final class CustomLogoutFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogoutFilter.class);

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
        String requestUri = request.getRequestURI();
        String requestMethod = request.getMethod();
        logger.info("Received request to {} with method {}", requestUri, requestMethod);

        if (!requestUri.equals("/logout") || !requestMethod.equals("POST")) {
            filterChain.doFilter(request, response);
            return;
        }

        String refresh = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                logger.info("Cookie: {} = {}", cookie.getName(), cookie.getValue()); // Debug log
                if (cookie.getName().equals("refresh")) {
                    refresh = cookie.getValue();
                    break;
                }
            }
        }

        if (refresh == null) {
            logger.warn("No refresh token found in cookies");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try {
            jwtUtil.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            logger.warn("Refresh token is expired");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {
            logger.warn("Invalid token category: {}", category);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if (!isExist) {
            logger.warn("Refresh token not found in repository");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Proceed with logout
        refreshRepository.deleteByRefresh(refresh);

        Cookie cookie = new Cookie("refresh", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        removeSameSiteCookie(response, "refresh");

        response.setStatus(HttpServletResponse.SC_OK);
        logger.info("Successfully logged out");
    }

    private void removeSameSiteCookie(HttpServletResponse response, String name) {
        StringBuilder cookie = new StringBuilder();
        cookie.append(name).append("=;");
        cookie.append("Max-Age=0;");
        cookie.append("Path=/;");
        cookie.append("SameSite=None;");
        response.addHeader("Set-Cookie", cookie.toString());
    }
}