package lkm.starterproject.auth.config;

import jakarta.servlet.http.HttpServletRequest;
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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

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
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));    //해당 server port 허용
                        configuration.setAllowedMethods(Collections.singletonList("*"));        //get, post등의 모든 요청 허용
                        configuration.setAllowCredentials(true);        //쿠키 및 인증데이터 보내도 되는지 허용(자격증명 등에 사용)
                        configuration.setAllowedHeaders(Collections.singletonList("*"));    //모든 http헤더 허용
                        configuration.setMaxAge(3600L);     //실행전 요청 응답 캐시할 허용시간(1시간)
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));        //응답에서 브라우저에 노출될 헤더 지정 Authorization헤더
                        return configuration;
                    }
                })));
        http    //csrf -> disable 설정(session방식은 csrf를 필수적으로 방어해야하지만 jwt방식은 session을 stateless방식으로 관리하여 비활성화해도됨
                .csrf( (auth) -> auth.disable() );  //csrf(Cross Site Request Forgery : 사이트간 위조 요청) get을 제외한 post, put, delete요청으로부터 보호
        http
                .formLogin( (auth) -> auth.disable() ); //Form로그인 방식 비활성화
        http
                .httpBasic( (auth) -> auth.disable() ); //http basic 인증방식 비활성화
        http
                .authorizeHttpRequests( (auth) -> auth      //경로별 인가작업
                        .requestMatchers("/**").permitAll()        /*개발용(추후 삭제)*/
//배포용                        .requestMatchers("/auth/login", "/login", "/", "/auth/signup", "/reissue").permitAll()    // 해당 경로는 모든권한 허용
                        .requestMatchers("/admin").hasRole(Role.ADMIN.name())     // 해당경로는 admin 권한대상자만 사용
                        .anyRequest().authenticated());     //기타 경로는 로그인한 사용자만 사용가능
        //---------------필터---------------------
        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);    //JWT필터 제일먼저 실행 토큰을 기반으로 요청을 인증
        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class);   //로그아웃 요청을 처리하여 올바르게 로그아웃되도록 함
        http    //기존의 필터를 LoginFilter로 대체함, AuthenticationManager()와 JWTUtil 전달, 로그인요청 처리 인증성공시 JWT 생성
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshRepository), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement((session) -> session                         //세션 관리를 stateless로 구성
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));       //JWT방식 인증/인가 방식은 session을 stateless방식으로 반드시 설정해야함
        return http.build();
    }
}