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
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)  //csrf(Cross Site Request Forgery : 사이트간 위조 요청) get을 제외한 post, put, delete요청으로부터 보호
                .formLogin(AbstractHttpConfigurer::disable) //Form로그인 방식 비활성화
                .httpBasic(AbstractHttpConfigurer::disable) //http basic 인증방식 비활성화
                .authorizeHttpRequests(auth -> auth      //경로별 인가작업
                        .requestMatchers("/", "/auth/login", "/auth/signup","/login", "/logout", "/reissue").permitAll()    // 해당 경로는 모든권한 허용
                        .requestMatchers("/admin").hasRole(Role.ADMIN.name())     // 해당경로는 admin 권한대상자만 사용
                        .anyRequest().authenticated())     //기타 경로는 로그인한 사용자만 사용가능
        //---------------필터---------------------
                .sessionManagement((session) -> session                         //세션 관리를 stateless로 구성
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))       //JWT방식 인증/인가 방식은 session을 stateless방식으로 반드시 설정해야함
                .addFilterBefore(new JWTFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)    //JWT필터 제일먼저 실행 토큰을 기반으로 요청을 인증
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LogoutFilter.class)   //로그아웃 요청을 처리하여 올바르게 로그아웃되도록 함
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshRepository), UsernamePasswordAuthenticationFilter.class);    //기존의 필터를 LoginFilter로 대체함, AuthenticationManager()와 JWTUtil 전달, 로그인요청 처리 인증성공시 JWT 생성
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {  //cors설정
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}