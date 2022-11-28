package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountType {
    private Long id;                // 고유 번호
    private String description;     // 거래 내용
    private double interestRate;    // 게좌 이자율
    private String name;            // 고객 이름

}
