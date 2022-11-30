package com.example.databaseproject.dto.account.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateAccountTypeRequestDto {
    private String description;     // 계좌 거래 내용
    private double interestRate;    // 계좌 이자율
    private String name;            // 계좌 고객 이름
}
