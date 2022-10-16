package com.example.databaseproject.dto.account.request;

import lombok.Value;

@Value
public class CreateAccountTypeRequestDto {
    private String description;
    private double interestRate;
    private String name;
}
