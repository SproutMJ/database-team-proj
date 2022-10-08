package com.example.databaseproject.dto.auth.request;

import lombok.Value;

@Value
public class UserRegisterRequestDto {
    private final String username;
    private final String password;

    private final String address;
    private final String email;
    private final String phone;
    private final String job;
}
