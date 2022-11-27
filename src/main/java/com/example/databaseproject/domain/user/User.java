package com.example.databaseproject.domain.user;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Value
public class User {
    private Long id;            // 예금 계좌 ID
    private String username;    // 고객명
    private String password;    // 비밀번호
    private Collection<? extends GrantedAuthority> authorities; // 권한(?)
    private String address;     // 고객 주소
    private String email;       // 고객 이메일
    private String phone;       // 고객 전화번호
    private String job;         // 고객 직업
    // 고객 생년월일(?)
}
