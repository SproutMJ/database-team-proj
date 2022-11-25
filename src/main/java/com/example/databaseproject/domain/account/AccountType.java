package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountType {
    private Long id;                // 예금 계좌 ID
    private String description;     // 예금 내용
    private double interestRate;    // 이자
    private String name;            // 예금자 이름

}
