package com.example.databaseproject.dto.account.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateAccountTypeRequestDto {
    private String description;
    private double interestRate;
    private String name;
}
