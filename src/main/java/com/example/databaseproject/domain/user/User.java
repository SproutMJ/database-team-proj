package com.example.databaseproject.domain.user;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

@Value
public class User {
    private String username;        // 고객 이름
    private String address;         // 고객 주소
    private String email;           // 고객 이메일
    private String phone;           // 고객 전화번호
    private String job;             // 고객 직업
    private Date birthday;          // 고객 생일
    private String socialNumber;    // 고객 주민번호
    private Long id;                // 고유 번호
//    private String password;
//    private Collection<? extends GrantedAuthority> authorities;
}
