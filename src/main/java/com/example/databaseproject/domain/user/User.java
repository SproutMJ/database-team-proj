package com.example.databaseproject.domain.user;

import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Value
public class User {
    private Long id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
}
