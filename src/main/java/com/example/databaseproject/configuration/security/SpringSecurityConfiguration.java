package com.example.databaseproject.configuration.security;

import com.example.databaseproject.configuration.security.jwt.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @apiNote
 * SpringSecurity에 대한 Configuration
 * // TODO: 2022-09-12 SpringSecurity가 5.7로 업데이트됨에 따라 @Override가 아니라 @Bean으로 설정해야 함
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .cors() //교차출처 리소스 공유(CORS) 설정
                .and()
                .csrf() //CSRF(Cross Site Request Forgery) 사이트 간 요청 위조 설정
                .disable()
//                .exceptionHandling() //(3)
//                .authenticationEntryPoint(unauthorizedHandler)
//                .and()
//                .sessionManagement() //(4)
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authorizeRequests() // (5)
//                .antMatchers(RestControllerBase.API_URI_PREFIX + "/auth/**")
//                .permitAll()
//                .antMatchers(RestControllerBase.API_URI_PREFIX + "/**")
//                .authenticated()
//                .and()
//                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .formLogin().disable().headers().frameOptions().disable();

        //http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
