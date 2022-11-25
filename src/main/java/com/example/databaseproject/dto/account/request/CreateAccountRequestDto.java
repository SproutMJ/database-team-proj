package com.example.databaseproject.dto.account.request;

import lombok.Value;

@Value
public class CreateAccountRequestDto {
    private boolean cardRegistered;     // 카드 신청 여부
    private Long accountType;           // 예금 계좌 종류
    private String customerName;        // 예금자 이름
    private String phoneNumber;         // 전화번호
    private String email;               // 이메일
    private String socialNumber;        // 고객 주민 번호
}
