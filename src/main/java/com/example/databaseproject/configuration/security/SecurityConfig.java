package com.example.databaseproject.configuration.security;

import com.example.databaseproject.configuration.security.filter.JwtAuthenticationFilter;
import com.example.databaseproject.configuration.security.jwt.provider.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .cors().and()
                .csrf().disable();
        //http.httpBasic().disable(); // 일반적인 루트가 아닌 다른 방식으로 요청시 거절하며, header에 id, pw가 아닌 token(jwt)을 달고 간다. 그래서 basic이 아닌 bearer을 사용
        http.httpBasic().disable()
                .authorizeRequests()// 요청에 대한 사용권한 체크
                .antMatchers("/auth/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().disable() //로그인폼 비활성화
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class); // 필터체인 중 UsernamePasswordAuthenticationFilter 앞에 JwtAuthenticationFilter 삽입
        //세션 무효화
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    /**
     * @apiNote bcrypt 해시기
     * @return
     */
    @Bean
    public PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
