package com.example.databaseproject.configuration.security.jwt.provider;

import com.example.databaseproject.configuration.security.usermanager.CustomUserDetailsManager;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;

/**
 * @apiNote CustomProvider로서 토큰 생성, 토큰 검증, 헤더에서 토큰 추출, Authentication 객체로 전환 등의 책임을 가진다.
 * // TODO: 2022/09/17 refreshToken 구현 
 */
@Component
public class JwtTokenProvider {
    private final String secretKey; // application 파일에 저장된 값에 의존
    
    private long tokenValidTime = 30 * 60 * 1000L; // 토큰 유효시간 30분

    private final CustomUserDetailsManager userDetailsManager;

    public JwtTokenProvider(@Value("${jwt.secret}") String propertyKey, CustomUserDetailsManager userDetailsManager) {
        this.secretKey = Base64.getEncoder().encodeToString(propertyKey.getBytes());
        this.userDetailsManager = userDetailsManager;
    }

    /**
     * @apiNote 토큰 생성 메서드
     * @param userPk
     * @param roles
     * @return token
     */
    public String createToken(String userPk, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();
    }

    /**
     * @apiNote 토큰을 검증해 Authentication를 반환
     * @param token
     * @return Authentication
     */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsManager.loadUserByUsername(this.getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * @apiNote 토큰에서 username 추출
     * @param token
     * @return
     */
    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * @apiNote header에서 "Authorization" 값 추출
     * @param request
     * @return
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    /**
     * @apiNote 만료를 포함한 토큰 유효여부 검증
     * @param jwtToken
     * @return
     */
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
