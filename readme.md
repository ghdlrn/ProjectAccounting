백엔드 개발환경
java 21
springboot 3.2.4

종속성
lombok
springboot devtool
spring web
spring security 6.2.3
JWT 0.12.3
querydsl 5.0.0

DB
spring data JPA
postgreSQL
H2

프론트엔드 개발환경
Node.js ( 20.12.1 )
Vue3
Nuxt3 ( 3.11.2 )
Vuetify3 ( 3.5.15)
Pinia ( 2.1.7 )
Pinia/nuxt ( 0.5.1 )
vee-validate
package lkm.starterproject.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
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
package lkm.starterproject.auth.config;

import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.jwt.CustomLogoutFilter;
import lkm.starterproject.auth.jwt.JWTFilter;
import lkm.starterproject.auth.jwt.JWTUtil;
import lkm.starterproject.auth.jwt.LoginFilter;
import lkm.starterproject.auth.repository.RefreshRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {        //비밀번호를 암호화해서 검증
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http    //cors설정
                .cors((cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));    //해당 server port 허용
                    config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));        //get, post등의 모든 요청 허용
                    config.setAllowedHeaders(Collections.singletonList("*"));    //모든 http헤더 허용
                    config.setAllowCredentials(true);        //쿠키 및 인증데이터 보내도 되는지 허용(자격증명 등에 사용)
                    config.setMaxAge(3600L);     //실행전 요청 응답 캐시할 허용시간(1시간)
                    config.setExposedHeaders(Collections.singletonList("Authorization"));        //응답에서 브라우저에 노출될 헤더 지정 Authorization헤더
                    return config;
                })))
                //csrf -> disable 설정(session방식은 csrf를 필수적으로 방어해야하지만 jwt방식은 session을 stateless방식으로 관리하여 비활성화해도됨
                .csrf(AbstractHttpConfigurer::disable)  //csrf(Cross Site Request Forgery : 사이트간 위조 요청) get을 제외한 post, put, delete요청으로부터 보호
                .formLogin(AbstractHttpConfigurer::disable) //Form로그인 방식 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) //http basic 인증방식 비활성화
                .authorizeHttpRequests(auth -> auth      //경로별 인가작업
                        .requestMatchers("/auth/login", "/login", "/", "/auth/signup", "/reissue", "/logout").permitAll()    // 해당 경로는 모든권한 허용
                        .requestMatchers("/admin").hasRole(Role.ADMIN.name())     // 해당경로는 admin 권한대상자만 사용
                        .anyRequest().authenticated())     //기타 경로는 로그인한 사용자만 사용가능
        //---------------필터---------------------
                .sessionManagement((session) -> session                         //세션 관리를 stateless로 구성
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))       //JWT방식 인증/인가 방식은 session을 stateless방식으로 반드시 설정해야함
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)    //JWT필터 제일먼저 실행 토큰을 기반으로 요청을 인증
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class)   //로그아웃 요청을 처리하여 올바르게 로그아웃되도록 함
                //기존의 필터를 LoginFilter로 대체함, AuthenticationManager()와 JWTUtil 전달, 로그인요청 처리 인증성공시 JWT 생성
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshRepository), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}package lkm.starterproject.auth.constants;

public enum Role {
ADMIN,
USER,
MASTER,
NORMAL,
}
package lkm.starterproject.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping("/admin")
    public String Admin() {

        return "Admin Controller";
    }
}
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
    public ResponseEntity<String> SignUp(@RequestBody MemberDto memberDto) {     //회원가입
        memberService.SignUp(memberDto);      //SignService에서 저장받은 회원정보(memberDto) 저장
        return ResponseEntity.ok("Sign up successful");
    }

}
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
package lkm.starterproject.auth.controller;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lkm.starterproject.auth.entity.Refresh;
import lkm.starterproject.auth.repository.RefreshRepository;
import lkm.starterproject.auth.jwt.JWTUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ReissueController(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        String refresh = null;
        Cookie[] cookies = request.getCookies();        //요청을 받으면 refresh토큰을 쿠키에 담음
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {      //refresh 토큰 정보가 없으면 해당 상태코드 메세지 발송
            return new ResponseEntity<>("refresh token null", HttpStatus.BAD_REQUEST);
        }

        try {
            jwtUtil.isExpired(refresh); //refresh톸느 만료여부 확인, 만료되면 해당 상태코드 발송
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("refresh token expired", HttpStatus.BAD_REQUEST);
        }

        String category = jwtUtil.getCategory(refresh);
        if (!category.equals("refresh")) {      //토큰 종류가 refresh인지 확인 아니면 해당 상태코드 발송
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);
        }

        Boolean isExist = refreshRepository.existsByRefresh(refresh);   //DB에 refresh토큰이 저장되어 있는지 확인
        if (!isExist) {
            return new ResponseEntity<>("invalid refresh token", HttpStatus.BAD_REQUEST);   //없으면 해당 상태코드 발송
        }

        String email = jwtUtil.getEmail(refresh);
        String role = jwtUtil.getRole(refresh);

        String newAccess = jwtUtil.createJwt("access", email, role, 600000L);   //새 JWT Access, Refresh토큰 발행
        String newRefresh = jwtUtil.createJwt("refresh", email, role, 86400000L);

        refreshRepository.deleteByRefresh(refresh); //DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        addRefreshEntity(email, newRefresh, 86400000L);

        response.setHeader("access", newAccess);    //response
        response.addCookie(createCookie("refresh", newRefresh));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addRefreshEntity(String email, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = new Refresh();
        refreshEntity.setEmail(email);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}package lkm.starterproject.auth.dto;

import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private final Member member;

    public CustomUserDetails(Member member) {
        this.member = member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) member.getRole()::name);
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
package lkm.starterproject.auth.dto;

import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
public class MemberCompanyDto {

    private Long id;

    private MemberDto member;

    private CompanyDto company;

    private Role role;

    private boolean currentCompany;
}
package lkm.starterproject.auth.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private String username;

    private String email;

    private String password;
}package lkm.starterproject.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
public abstract class BaseEntity extends BaseTimeEntity {

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;   //생성

    @LastModifiedBy
    private String modifiedBy;  //수정
}
package lkm.starterproject.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@Setter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;      //등록시간

    @LastModifiedDate
    private LocalDateTime updateTime;   //업데이트시간
}
package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {     //회원

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable=false, length=16)
    private String username;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false, unique=true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;      //권한
    /*------------------------------------------------------------------------------------
     * ----------------------------------Mapping------------------------------------------
     * ----------------------------------------------------------------------------------*/
    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberCompany> memberCompanies = new ArrayList<>();


}
package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.auth.constants.Role;
import lombok.*;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "member_company")
public class MemberCompany extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_company_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    private Role role;    //회원의 회사에서의 권한

    private boolean currentCompany; //현재 사용중인 회사 여부
}package lkm.starterproject.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "refresh")
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "refresh_id")
    private Long id;

    private String email;

    private String refresh;

    private String expiration;  //토큰 만료시간
}package lkm.starterproject.auth.jwt;

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
        Member member = new Member();
        member.setEmail(email);
        member.setRole(Role.valueOf(role));

        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);    //SecurityContextHolder에 유저정보를 담아 일시적인 세션 생성

        filterChain.doFilter(request, response);
    }
}package lkm.starterproject.auth.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private final SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public String getEmail(String token) {   //email토큰 검증 메서드
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
        //토큰이 내 서버에서 생성되었는지   생성된 키가 내가 가진 secretKet와 일치하는지 확인      email을 String으로 가져옴
    }

    public String getRole(String token) {       //role(권한) 토큰 검증 메서드
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public String getCategory(String token) {   //토큰 종류 구별(refesh, access)
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("category", String.class);
    }

    public Boolean isExpired(String token) {      //토큰 만료 확인 검증 메서드 소멸되면 true, 아직 남아있으면 false
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String category, String email, String role, Long expiredMs) { //토큰 생성 메서드
                    //category : 토큰 종류
        return Jwts.builder()
                .claim("category", category)
                .claim("email", email)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))     //토큰 발행 시간
                .expiration(new Date(System.currentTimeMillis() + expiredMs))   //토큰 소멸시간
                .signWith(secretKey)    //토큰 암호화
                .compact();
    }
}
package lkm.starterproject.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
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

        String access = jwtUtil.createJwt("access", email, role, 600000L);       //access토큰 생성 10분뒤 소멸
        String refresh = jwtUtil.createJwt("refresh", email, role, 86400000L);       //refresh토큰 생성 24시간 뒤 소멸
        addRefreshEntity(email, refresh, 86400000L);    //Refresh 토큰 저장
        // 쿠키 생성 및 설정
        Cookie accessTokenCookie = createCookie("access", access);
        Cookie refreshTokenCookie = createCookie("refresh", refresh);
        res.addCookie(accessTokenCookie);
        res.addCookie(refreshTokenCookie);

        setSameSiteCookie(res, "access", access, 86400, true, true);
        setSameSiteCookie(res, "refresh", refresh, 86400, true, true);
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

    private Cookie createCookie(String key, String value) { //value : JWT값
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60); // 1 day
//        cookie.setSecure(true);  https환경에서 사용
cookie.setPath("/");
cookie.setHttpOnly(true);
return cookie;
}

    private void setSameSiteCookie(HttpServletResponse response, String name, String value, int maxAge, boolean httpOnly, boolean secure) {
        StringBuilder cookie = new StringBuilder();
        cookie.append(name).append("=").append(value).append(";");
        cookie.append("Max-Age=").append(maxAge).append(";");
        cookie.append("Path=/;");
        /*if (secure) {
            cookie.append("Secure;");
        }*/
        if (httpOnly) {
            cookie.append("HttpOnly;");
        }
       // cookie.append("SameSite=None;");
        response.addHeader("Set-Cookie", cookie.toString());
    }
}package lkm.starterproject.auth.jwt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginCredentials {
@JsonProperty("email")
private String email;

    @JsonProperty("password")
    private String password;

    public UserLoginCredentials() {
        // 기본 생성자는 JSON 역직렬화를 위해 필요합니다.
    }

    public UserLoginCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
package lkm.starterproject.auth.mapper;

import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.auth.dto.MemberCompanyDto;
import lkm.starterproject.auth.entity.MemberCompany;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
uses = { MemberMapper.class, CompanyMapper.class })
public interface MemberCompanyMapper {

    MemberCompanyDto toDto(MemberCompany entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달

    MemberCompany toEntity(MemberCompanyDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

}package lkm.starterproject.auth.mapper;

import lkm.starterproject.auth.dto.MemberDto;
import lkm.starterproject.auth.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    MemberDto toDto(Member entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "USER")
    @Mapping(target = "memberCompanies", ignore = true)
    Member toEntity(MemberDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

}package lkm.starterproject.auth.repository;

import lkm.starterproject.auth.entity.MemberCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCompanyRepository extends JpaRepository<MemberCompany, Long> {
}
package lkm.starterproject.auth.repository;

import lkm.starterproject.auth.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Boolean existsByEmail(String email);

    Member findByEmail(String email);
}
package lkm.starterproject.auth.repository;

import jakarta.transaction.Transactional;
import lkm.starterproject.auth.entity.Refresh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshRepository extends JpaRepository<Refresh, Long> {

    Boolean existsByRefresh(String refresh);        //Refresh토큰이 존재하는지 확인

    @Transactional
    void deleteByRefresh(String refresh);       //DB에서 Refresh토큰을 지움
}package lkm.starterproject.auth.service;

import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.auth.repository.MemberRepository;
import lkm.starterproject.auth.dto.CustomUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new UsernameNotFoundException("Member not found with email: " + email);
        }

        return new CustomUserDetails(member);
    }
}
package lkm.starterproject.auth.service;

import lkm.starterproject.auth.entity.Member;
import lkm.starterproject.auth.mapper.MemberMapper;
import lkm.starterproject.auth.repository.MemberRepository;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.dto.MemberDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper,
                         BCryptPasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public void SignUp(MemberDto memberDto) {   //외원가입
        String email = memberDto.getEmail();

        if (memberRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        Member member = memberMapper.toEntity(memberDto);
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        member.setRole(Role.USER);
        memberRepository.save(member);
    }
}
package lkm.starterproject.accounting.entity.company;

import jakarta.persistence.*;
import lkm.starterproject.accounting.entity.basic.Address;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.register.AccountTitle;
import lkm.starterproject.accounting.entity.register.Card;
import lkm.starterproject.accounting.entity.register.Customer;
import lkm.starterproject.accounting.entity.register.Finance;
import lkm.starterproject.auth.entity.BaseEntity;
import lkm.starterproject.auth.entity.MemberCompany;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter @ToString
@Builder @NoArgsConstructor @AllArgsConstructor
@Table(name = "company")
public class Company extends BaseEntity {  //회사

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;        //회사코드

    private String licenseType;    //사업자 유형

    private String headOfficeStatus;      //본점여부

    private String paymentHeadOfficeStatus;        //본점일괄납부여부

    @Column(nullable = false)
    private String name;        //회사명

    @Column(nullable = false, length = 12, unique = true)
    private String businessRegistrationNumber;      //사업자등록번호

    private String nameOfRepresentative;        //대표자명

    @Column(length = 14)
    private String corporationRegistrationNumber;       //법인등록번호

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String businessType;        //업태

    private String businessItem;        //업종
/*------------------------------------------------------------------------------------
* ----------------------------------회계 / 세무 정보------------------------------------
* ----------------------------------------------------------------------------------*/
  private Long fiscalYearClass;        //회계연도기수

  private LocalDate fiscalYearStart;  //회계연도시작일

  private LocalDate fiscalYearEnd;    //회계연도마감일

  private LocalDate privatePracticeDate;      //개업연월일

  @ManyToOne
  @JoinColumn(name = "tax_office_id")
  private TaxOffice taxOffice;        //사업장 세무서

  @ManyToOne
  @JoinColumn(name = "local_tax_id")
  private LocalTax localTax;          //지방세 법정동

  private String finance;            //국세환급금 계좌

  private String corporationClassifyStatus;    //법인구분

  private String companyTypeStatus;    //회사종류구분

  private String businessScaleStatus;    //중소기업여부

  private String localTaxBillDivision;       //지방세 신고 구분

  @Column(length = 14)
  private String residentRegistrationNumber;      //대표자 주민번호
  /*------------------------------------------------------------------------------------
    * ----------------------------------기타 정보------------------------------------------
    * ----------------------------------------------------------------------------------*/
      @Column(length = 13)
      private String phone;   //사업장전화번호

  @Column(length = 13)
  private String fax;    //사업장팩스번호

  private String chargeName;  //담당자 이름

  private String chargeEmail;       //담당자 이메일

  private String note;    //비고
  /*------------------------------------------------------------------------------------
    * ----------------------------------Mapping------------------------------------------
    * ----------------------------------------------------------------------------------*/
      @Builder.Default
      @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
      private List<MemberCompany> memberCompanies = new ArrayList<>();

  @Builder.Default
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Customer> customers = new ArrayList<>();        //거래처

  @Builder.Default
  @OneToMany(mappedBy = "company",cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Finance> finances = new ArrayList<>();        //계좌

  @Builder.Default
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Card> cards = new ArrayList<>();        //카드

  @Builder.Default
  @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<AccountTitle> accountTitles = new ArrayList<>();        //카드
  }
  package lkm.starterproject.accounting.dto.company;

import jakarta.validation.constraints.NotBlank;
import lkm.starterproject.accounting.dto.basic.AddressDto;
import lkm.starterproject.accounting.dto.basic.LocalTaxDto;
import lkm.starterproject.accounting.dto.basic.TaxOfficeDto;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

    private Long id;  // 회사코드

    private String licenseType;  // 사업자 유형

    private String headOfficeStatus;  // 본점여부

    private String paymentHeadOfficeStatus;  // 본점일괄납부여부

    @NotBlank(message = "회사이름은 필수 입력사항 입니다")
    private String name;  // 회사명
    
    @NotBlank(message = "사업자등록번호는 필수 입력사항 입니다")
    private String businessRegistrationNumber;  // 사업자등록번호

    private String nameOfRepresentative;  // 대표자명

    private String corporationRegistrationNumber;  // 법인등록번호

    private AddressDto address;    //주소

    private String businessType;  // 업태

    private String businessItem;  // 업종

    private Long fiscalYearClass;  // 회계연도기수

    private LocalDate fiscalYearStart;  // 회계연도시작일

    private LocalDate fiscalYearEnd;  // 회계연도마감일

    private LocalDate privatePracticeDate;  // 개업연월일

    private TaxOfficeDto taxOffice;  // 사업장 세무서 코드

    private LocalTaxDto localTax;  // 지방세 법정동 코드

    private String finance;  // 국세환급금 계좌 코드

    private String corporationClassifyStatus;  // 법인구분

    private String companyTypeStatus;  // 회사종류구분

    private String businessScaleStatus;  // 중소기업여부

    private String localTaxBillDivision;  // 지방세 신고 구분

    private String residentRegistrationNumber;  // 대표자 주민번호

    private String phone;  // 사업장전화번호

    private String fax;  // 사업장팩스번호

    private String chargeName;  // 담당자 이름

    private String chargeEmail;  // 담당자 이메일

    private String note;  // 비고

}
package lkm.starterproject.accounting.mapper.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.basic.AddressMapper;
import lkm.starterproject.accounting.mapper.basic.LocalTaxMapper;
import lkm.starterproject.accounting.mapper.basic.TaxOfficeMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring",
uses = {AddressMapper.class, TaxOfficeMapper.class, LocalTaxMapper.class })
public interface CompanyMapper {

    CompanyDto toDto(Company entity); //엔티티 -> DTO 변환, 엔티티 데이터 -> 클라이언트 전달
    Company toEntity(CompanyDto dto); //DTO -> 엔티티 변환, 클라이언트가보낸 데이터를 엔티티로 변환

    List<CompanyDto> toDtoList(List<Company> entityList);

    void updateDto(CompanyDto dto, @MappingTarget Company entity);

}package lkm.starterproject.accounting.repository.company;

import lkm.starterproject.accounting.entity.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
package lkm.starterproject.accounting.service.company;

import lkm.starterproject.accounting.dto.company.CompanyDto;

import java.util.List;

public interface CompanyService {

    CompanyDto createCompany(CompanyDto companyDto, String email);

    List<CompanyDto> getAllCompanies();

    CompanyDto getCompany(Long id);

    CompanyDto updateCompany(Long id, CompanyDto companyDto);

    void deleteCompany(Long id);

    void selectCompany(Long companyId, String email);

    void assignRole(Long companyId, String email, String role);
}
package lkm.starterproject.accounting.service.impl.company;

import jakarta.persistence.EntityNotFoundException;
import lkm.starterproject.accounting.constants.UseStatus;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.entity.basic.LocalTax;
import lkm.starterproject.accounting.entity.basic.TaxOffice;
import lkm.starterproject.accounting.entity.company.Company;
import lkm.starterproject.accounting.mapper.company.CompanyMapper;
import lkm.starterproject.accounting.repository.company.CompanyRepository;
import lkm.starterproject.accounting.repository.basic.LocalTaxRepository;
import lkm.starterproject.accounting.repository.basic.TaxOfficeRepository;
import lkm.starterproject.accounting.service.company.CompanyService;
import lkm.starterproject.auth.constants.Role;
import lkm.starterproject.auth.entity.Member;

import lkm.starterproject.auth.entity.MemberCompany;
import lkm.starterproject.auth.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final MemberRepository memberRepository;
    private final TaxOfficeRepository taxOfficeRepository;
    private final LocalTaxRepository localTaxRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper, MemberRepository memberRepository,
                              TaxOfficeRepository taxOfficeRepository, LocalTaxRepository localTaxRepository) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
        this.memberRepository = memberRepository;
        this.taxOfficeRepository = taxOfficeRepository;
        this.localTaxRepository = localTaxRepository;
    }

    @Override
    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto, String email) {
        Company company = companyMapper.toEntity(companyDto);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        MemberCompany memberCompany = new MemberCompany();
        memberCompany.setMember(member);
        memberCompany.setCompany(company);
        memberCompany.setRole(Role.MASTER);
        memberCompany.setCurrentCompany(true);
        company.getMemberCompanies().add(memberCompany);
        assignLocalTaxAndTaxOffice(company, companyDto);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.toDtoList(companies);
    }

    @Override
    @Transactional(readOnly = true)
    public CompanyDto getCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional
    public CompanyDto updateCompany(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        assignLocalTaxAndTaxOffice(company, companyDto);
        companyMapper.updateDto(companyDto, company);
        company = companyRepository.save(company);
        return companyMapper.toDto(company);
    }

    @Override
    @Transactional
    public void deleteCompany(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        companyRepository.delete(company);
    }

    @Override
    @Transactional
    public void selectCompany(Long companyId, String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        // 모든 회사의 useStatus를 false로 변경
        for (MemberCompany memberCompany : member.getMemberCompanies()) {
            memberCompany.setCurrentCompany(false);
        }
        // 선택한 회사의 currentCompany를 true로 변경
        MemberCompany selectedCompany = member.getMemberCompanies().stream()
                .filter(mc -> mc.getCompany().getId().equals(companyId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("MemberCompany not found"));
        selectedCompany.setCurrentCompany(true);

        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void assignRole(Long companyId, String email, String role) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company 정보를 찾을 수 없음"));
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new EntityNotFoundException("Member not found");
        }
        for (MemberCompany memberCompany : company.getMemberCompanies()) {
            if (memberCompany.getMember().getEmail().equals(email)) {
                memberCompany.setRole(Role.valueOf(role));
                return;
            }
        }
        MemberCompany newMemberCompany = new MemberCompany();
        newMemberCompany.setMember(member);
        newMemberCompany.setCompany(company);
        newMemberCompany.setRole(Role.valueOf(role));
        company.getMemberCompanies().add(newMemberCompany);
    }

    private void assignLocalTaxAndTaxOffice(Company company, CompanyDto companyDto) {
        if (companyDto.getLocalTax() != null && companyDto.getLocalTax().getId() != null) {
            company.setLocalTax(findLocalTax(companyDto.getLocalTax().getId()));
        } else {
            company.setLocalTax(null);
        }

        if (companyDto.getTaxOffice() != null && companyDto.getTaxOffice().getId() != null) {
            company.setTaxOffice(findTaxOffice(companyDto.getTaxOffice().getId()));
        } else {
            company.setTaxOffice(null);
        }
    }

    private LocalTax findLocalTax(Long id) {
        return id == null ? null : localTaxRepository.findById(id).orElse(null);
    }

    private TaxOffice findTaxOffice(Long id) {
        return id == null ? null : taxOfficeRepository.findById(id).orElse(null);
    }


}
package lkm.starterproject.accounting.controller.company;

import jakarta.validation.Valid;
import lkm.starterproject.accounting.dto.company.CompanyDto;
import lkm.starterproject.accounting.service.company.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        CompanyDto createdCompany = companyService.createCompany(companyDto, email);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompany(@PathVariable("id") Long id) {
        CompanyDto companyDto = companyService.getCompany(id);
        return ResponseEntity.ok(companyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable("id") Long id, @RequestBody CompanyDto companyDto) {
        CompanyDto updatedCompany = companyService.updateCompany(id, companyDto);
        return ResponseEntity.ok(updatedCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/select")
    public ResponseEntity<Void> selectCompany(@RequestBody Long companyId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        companyService.selectCompany(companyId, email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/assign-role")
    public ResponseEntity<Void> assignRole(@PathVariable("id") Long companyId, @RequestParam("email") String email, @RequestParam("role") String role) {
        companyService.assignRole(companyId, email, role);
        return ResponseEntity.ok().build();
    }
}

package lkm.starterproject.accounting.constants;

public enum UseStatus {     //사용구분
USE,        //사용
UNUSED,     //미사용
}
plugins {
id 'java'
id 'org.springframework.boot' version '3.2.4'
id 'io.spring.dependency-management' version '1.1.4'
}

group = 'LKM'
version = '0.0.1-SNAPSHOT'

java {
sourceCompatibility = '21'
}

repositories {
mavenCentral()
}

dependencies {
implementation 'org.springframework.boot:spring-boot-starter'
implementation 'org.springframework.boot:spring-boot-starter-security'
implementation 'org.springframework.boot:spring-boot-starter-web'
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.projectlombok:lombok'
implementation 'org.springframework.boot:spring-boot-starter-validation'
compileOnly 'org.projectlombok:lombok'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.springframework.security:spring-security-test'
developmentOnly 'org.springframework.boot:spring-boot-devtools'
runtimeOnly 'org.postgresql:postgresql'

    //Querydsl 5.0.0
    implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
    annotationProcessor "com.querydsl:querydsl-apt:${dependencyManagement.importedProperties['querydsl.version']}:jakarta"
    annotationProcessor "jakarta.annotation:jakarta.annotation-api"
    annotationProcessor "jakarta.persistence:jakarta.persistence-api"

    //JWT 0.12.3
    implementation 'io.jsonwebtoken:jjwt-api:0.12.3'
    implementation 'io.jsonwebtoken:jjwt-impl:0.12.3'
    implementation 'io.jsonwebtoken:jjwt-jackson:0.12.3'

    //csv파일 읽기
    implementation 'org.apache.commons:commons-csv:1.8'

    //MapStruct
    implementation 'org.mapstruct:mapstruct:1.5.5.Final'
    implementation 'org.projectlombok:lombok-mapstruct-binding:0.2.0'
    annotationProcessor 'org.mapstruct:mapstruct-processor:1.5.5.Final'
    annotationProcessor 'org.projectlombok:lombok-mapstruct-binding:0.2.0'


}

tasks.named('test') {
useJUnitPlatform()
}

def querydslDir = "src/main/generated"

sourceSets {
main.java.srcDirs += [ querydslDir ]
}

tasks.withType(JavaCompile).configureEach {
options.getGeneratedSourceOutputDirectory().set(file(querydslDir))
}

clean.doLast {
file(querydslDir).deleteDir()
}

{
"name": "nuxt-app",
"private": true,
"type": "module",
"scripts": {
"build": "nuxt build",
"dev": "nuxt dev",
"generate": "nuxt generate",
"preview": "nuxt preview"
},
"dependencies": {
"@ant-design/icons-vue": "^7.0.1",
"@mdi/font": "^7.4.47",
"@pinia/nuxt": "^0.5.1",
"@popperjs/core": "^2.11.8",
"@vee-validate/rules": "^4.12.6",
"ant-design-vue": "^4.2.1",
"apexcharts": "^3.48.0",
"axios": "^1.6.8",
"nuxt": "^3.11.2",
"pinia": "^2.1.7",
"v-calendar": "^3.1.2",
"vee-validate": "^4.5.8",
"vue": "^3.4.21",
"vue-i18n": "^9.13.1",
"vue-router": "^4.3.0",
"vue-screen-utils": "^1.0.0-beta.13",
"vue-tabler-icons": "^2.21.0",
"vue3-apexcharts": "^1.5.2",
"vue3-easy-data-table": "^1.5.47",
"vue3-perfect-scrollbar": "^2.0.0",
"vue3-print-nb": "^0.1.4"
},
"devDependencies": {
"@nuxt/types": "^2.17.3",
"sass": "^1.75.0",
"sass-loader": "^14.2.0",
"vite-plugin-vuetify": "^2.0.3",
"vuetify": "^3.5.15"
}
}
import axios from 'axios';
import { defineNuxtPlugin } from '#app';
import { useAuthStore } from '~/stores/auth/auth';

export default defineNuxtPlugin((nuxtApp) => {
const api = axios.create({
baseURL: process.env.VITE_API_URL || 'http://localhost:8080',
withCredentials: true
});

    api.interceptors.request.use(
        (config) => {
            const authStore = useAuthStore();
            if (authStore.member?.accessToken) {
                config.headers['Authorization'] = `Bearer ${authStore.member.accessToken}`;
            }
            return config;
        },
        (error) => Promise.reject(error)
    );

    api.interceptors.response.use(
        (response) => response,
        async (error) => {
            const authStore = useAuthStore();
            if (error.response?.status === 401) {
                await authStore.refreshToken();
                return api(error.config);
            }
            return Promise.reject(error);
        }
    );

    return {
        provide: {
            api
        }
    };
});
import { defineStore } from 'pinia';
import { useRouter } from 'vue-router';
import { useNuxtApp } from '#app';

export const useAuthStore = defineStore('auth', {
state: () => ({
member: JSON.parse(localStorage.getItem('member') || '{}'),
returnUrl: null
}),
actions: {
async login(email: string, password: string) {
try {
const response = await useNuxtApp().$api.post('/login', { email, password });
this.member = response.data;
localStorage.setItem('member', JSON.stringify(this.member));
const router = useRouter();
await router.push('/');
} catch (error: any) {
console.error('Failed to login:', error);
}
},
async logout() {
await useNuxtApp().$api.post('/logout').then(() => {
this.member = null;
localStorage.removeItem('member');
const router = useRouter();
router.push('/');
});
},
async refreshToken() {
try {
const response = await useNuxtApp().$api.post('/reissue');
this.member.accessToken = response.data.accessToken;
localStorage.setItem('member', JSON.stringify(this.member));
} catch (error: any) {
await this.logout();
}
},
async loadUserFromLocalStorage() {
const memberData = localStorage.getItem('member');
if (memberData) {
this.member = JSON.parse(memberData);
}
},
}
});
import { defineStore } from 'pinia';
import type {Company} from "~/types/accounting/company";
import {useNuxtApp} from "#app";

interface CompanyMember {
email: string;
role: string;
}

export const useCompanyStore = defineStore('company', {
state: () => ({
companies: [] as Company[],
currentCompany: null as Company | null,  // 현재 선택된 회사 정보
selectedCompany: null as Company | null,  // 현재 선택된 회사 정보
}),
actions: {
async fetchCompanies() {
try {
const response =  await useNuxtApp().$api.get('/register/company');
this.companies = response.data;
} catch (error: any) {
console.error('회사목록 조회 실패:', error.message);
throw new Error('회사목록 조회 실패');
}
},
async getCompany(id: number) {
try {
const response =  await useNuxtApp().$api.get(`/register/company/${id}`);
this.currentCompany = response.data;  // 현재 회사 정보를 currentCompany에 저장
} catch (error: any) {
console.error('회사정보 조회 실패:', error.message);
throw new Error('회사정보 조회 실패');
}
},
async createCompany(companyData: Partial<Company>) {
try {
const response =  await useNuxtApp().$api.post('/register/company', companyData);
this.companies.push(response.data);
alert('회사 정보가 등록되었습니다');
} catch (error: any) {
alert('회사등록 실패');
console.error('회사등록 실패:', error.message);
throw new Error('회사등록 실패');
}
},
async updateCompany(data: Company) {
try {
const response =  await useNuxtApp().$api.put(`/register/company/${data.id}`, data);
const index = this.companies.findIndex(company => company.id === data.id);
if (index !== -1) {
this.companies[index] = response.data;
alert('회사 정보가 수정되었습니다');
} else {
this.companies.push(response.data); // 새로운 데이터가 배열에 없다면 추가
alert('회사 정보가 등록되었습니다');
}
} catch (error: any) {
alert('회사 정보 수정 실패');
console.error('회사 정보 수정 실패:', error.message);
throw new Error('회사 정보 수정 실패');
}
},

        async deleteCompany(id: number) {
            try {
                const response =  await useNuxtApp().$api.delete(`/register/company/${id}`);
                if (response.status === 200) {
                    await this.fetchCompanies();
                    alert('회사 정보가 삭제되었습니다');
                } else {
                    alert('회사 정보 삭제에 실패했습니다');
                    console.error('회사 정보 삭제 실패:', response.status);
                    throw new Error('회사 정보 삭제 실패: 서버 응답 ' + response.status);
                }
            } catch (error: any) {
                alert('회사 정보 삭제에 실패했습니다');
                console.error('회사 정보 삭제 실패:', error.message);
                throw new Error('회사 정보 삭제 실패');
            }
        },

        async selectCompany(companyId: number) {
            this.selectedCompany = this.companies.find(company => company.id === companyId) || null;
             await useNuxtApp().$api.post('/api/company/select', {companyId});
        },

        async assignRole(companyId: number, email: string, role: string) {
            try {
                 await useNuxtApp().$api.post(`/api/company/${companyId}/assign-role`, { email, role });
            } catch (error) {
                // Handle error
            }
        },
    }
});

다음과 같은 프론트 - 백엔드 코드에서  로그인 후,
Company를 Create하는데 실패하여 아래와 같은 콘솔, network정보가 나타났다
회사등록 실패: Request failed with status code 403
createCompany @ company.ts:42
await in createCompany (async)
(anonymous) @ pinia.js?v=ebab23bb:1064
store.<computed> @ pinia.js?v=ebab23bb:745
saveOrUpdateCompany @ CompanyRegister.vue:35
cache.<computed>.cache.<computed> @ chunk-2435C5CU.js?v=ebab23bb:11034
callWithErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1660
callWithAsyncErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1667
emit @ chunk-2435C5CU.js?v=ebab23bb:2183
(anonymous) @ chunk-2435C5CU.js?v=ebab23bb:9251
onSubmit @ VForm.mjs?v=ebab23bb:36
callWithErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1660
callWithAsyncErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1667
invoker @ chunk-2435C5CU.js?v=ebab23bb:10287
Show 11 more frames
Show less
CompanyRegister.vue:39 Error saving or updating company:  Error: 회사등록 실패
at Proxy.createCompany (company.ts:43:23)
at async saveOrUpdateCompany (CompanyRegister.vue:35:7)
saveOrUpdateCompany @ CompanyRegister.vue:39
await in saveOrUpdateCompany (async)
cache.<computed>.cache.<computed> @ chunk-2435C5CU.js?v=ebab23bb:11034
callWithErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1660
callWithAsyncErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1667
emit @ chunk-2435C5CU.js?v=ebab23bb:2183
(anonymous) @ chunk-2435C5CU.js?v=ebab23bb:9251
onSubmit @ VForm.mjs?v=ebab23bb:36
callWithErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1660
callWithAsyncErrorHandling @ chunk-2435C5CU.js?v=ebab23bb:1667
invoker @ chunk-2435C5CU.js?v=ebab23bb:10287
Show 9 more frames
Show less
Request URL:
http://localhost:8080/register/company
Request Method:
POST
Status Code:
403 Forbidden
Remote Address:
[::1]:8080
Referrer Policy:
strict-origin-when-cross-origin
Access-Control-Allow-Credentials:
true
Access-Control-Allow-Origin:
http://localhost:3000
Access-Control-Expose-Headers:
Authorization
Cache-Control:
no-cache, no-store, max-age=0, must-revalidate
Connection:
keep-alive
Content-Length:
0
Date:
Tue, 21 May 2024 00:42:16 GMT
Expires:
0
Keep-Alive:
timeout=60
Pragma:
no-cache
Vary:
Origin
Vary:
Access-Control-Request-Method
Vary:
Access-Control-Request-Headers
X-Content-Type-Options:
nosniff
X-Frame-Options:
DENY
X-Xss-Protection:
0
Accept:
application/json, text/plain, */*
Accept-Encoding:
gzip, deflate, br, zstd
Accept-Language:
ko-KR
Connection:
keep-alive
Content-Length:
137
Content-Type:
application/json
Cookie:
SL_G_WPT_TO=ko; SL_GWPT_Show_Hide_tmp=undefined; SL_wptGlobTipTmp=undefined; access=eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6ImFjY2VzcyIsImVtYWlsIjoiZ2hkbHJuQGdtYWlsLmNvbSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzE2MjUxNTQ3LCJleHAiOjE3MTYyNTIxNDd9.jhJZvvSUYku2TDOzMjSdOdo1HUtWTX4ckvfYKrj9F80; refresh=eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6InJlZnJlc2giLCJlbWFpbCI6ImdoZGxybkBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcxNjI1MTU0NywiZXhwIjoxNzE2MzM3OTQ3fQ.I-8qp-uDYzrsGzzRflFzEbmTOhAFpcxKpM1xB5O1rgM
Host:
localhost:8080
Origin:
http://localhost:3000
Referer:
http://localhost:3000/
Sec-Ch-Ua:
"Not/A)Brand";v="8", "Chromium";v="126", "Google Chrome";v="126"
Sec-Ch-Ua-Mobile:
?0
Sec-Ch-Ua-Platform:
"Windows"
Sec-Fetch-Dest:
empty
Sec-Fetch-Mode:
cors
Sec-Fetch-Site:
same-site
User-Agent:
Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36
businessRegistrationNumber
:
"124-81-00998"
localTax
:
{}
name
:
"삼성전자(주)"
nameOfRepresentative
:
" 이규민"
taxOffice
:
{}SL_GWPT_Show_Hide_tmp	undefined	localhost	/	Session	30					Medium	SL_G_WPT_TO	ko	localhost	/	Session	13					Medium	SL_wptGlobTipTmp	undefined	localhost	/	Session	25					Medium	access	eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6ImFjY2VzcyIsImVtYWlsIjoiZ2hkbHJuQGdtYWlsLmNvbSIsInJvbGUiOiJVU0VSIiwiaWF0IjoxNzE2MjUxNTQ3LCJleHAiOjE3MTYyNTIxNDd9.jhJZvvSUYku2TDOzMjSdOdo1HUtWTX4ckvfYKrj9F80	localhost	/	2024-05-22T00:32:27.843Z	199	✓				Medium	refresh	eyJhbGciOiJIUzI1NiJ9.eyJjYXRlZ29yeSI6InJlZnJlc2giLCJlbWFpbCI6ImdoZGxybkBnbWFpbC5jb20iLCJyb2xlIjoiVVNFUiIsImlhdCI6MTcxNjI1MTU0NywiZXhwIjoxNzE2MzM3OTQ3fQ.I-8qp-uDYzrsGzzRflFzEbmTOhAFpcxKpM1xB5O1rgM	localhost	/	2024-05-22T00:32:27.843Z	202	✓				Medium
아래 요구사항에 맞게 문제가 되는 코드를 수정 추가하라
1. 보안구성은 Spring Security, JWT를 통해 AccessToken, RefreshToken을 이용하고 Refresh Token Rotation기능을 갖는다
2. 회원(Member), 회사(Company)는 다대다 관계이나 둘 사이 MemberCompany엔티티를 두고 관리한다
3. MemberCompany는 Member, Company와 연결되어 있으며 해당 회원의 Company정보에대해 CRUD할 수 있는 권한, 그리고 작업할 Company애 대한 currentCompany를 칼럼으로 갖는다
4. Member는 Company를 Create할 수 있으며 해당 작업을 진행한 Member는 MASTER의 권한을 갖는다
5. MASTER권한을 가진 Member는 다른 Member에게 같은 Company정보에 접근하여 Company를  Delete는 불가능하지만 Read, 그리고 Company에 종속된 다른 엔티티들을 CRUD할 수 있는 NORMAL의 권한을 갖도록 초대할 수 있다
6. MASTER권한을 가진 Member는 다른 Member의 권한을 수정할 수 있다
7. Member는 자신이 작업할 Company를 하나 선택할 수 있다(currentCompany가 true)
8. 회원 로그인시 Member는 자신이 작업할 Company를 자동으로 불러와 해당 Company의 작업을 수행할 수 있다   