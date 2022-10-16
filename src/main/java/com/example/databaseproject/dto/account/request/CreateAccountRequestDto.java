package com.example.databaseproject.dto.account.request;

import lombok.Value;

@Value
public class CreateAccountRequestDto {
    private boolean cardRegistered;
    private Long accountType;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String socialNumber;
}
