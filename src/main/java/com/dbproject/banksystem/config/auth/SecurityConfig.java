package com.dbproject.banksystem.config.auth;

import com.dbproject.banksystem.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면 사용 위해 해당 옵션들 비활성화
                .and()
                .authorizeRequests() // URL 별 권한 관리를 설정하는 옵션의 시작점 -> antMatcher 옵션 사용 가능
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**", "/profile").permitAll()
                // antMatchers
                // * 권한 관리 대상 지정하는 옵션
                // * URL, HTTP 메소드별로 관리 가능
                // * "/" 등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한 부여
                // * "/api/v1/**" 주소 가진 API는 USER 권한 가진 사람만 가능하도록 설정
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated()
                // anyRequest
                // * authenticatied() 추가하여 나머지 URL들은 모두 인증된 사용자에게만 허용
                // * 설정된 값 이외 나머지 URL 나타냄
                // * 인증된 사용자는 로그인한 사용자들을 말함
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        // OAuth 로그인 기능에 대한 여러 설정의 진입점
        // UserService
        // * 소셜 로그인 성공 후속 조치를 진행할 UserService 인터페이스 구현체 등록
        // * 리소스 서버(즉, 소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
    }
}