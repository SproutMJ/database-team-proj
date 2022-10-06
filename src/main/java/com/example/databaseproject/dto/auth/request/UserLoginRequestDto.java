package com.example.databaseproject.dto.auth.request;

import lombok.Value;

@Value
public class UserLoginRequestDto {
    private final String username;
    private final String password;
}
