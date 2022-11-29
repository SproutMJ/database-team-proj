package com.example.databaseproject.dto.account.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateAccountRequestDto {
    private Long cardApply;     // 카드 신청 여부
    private String accountType;           // 예금 계좌 종류
    private String userName;        // 예금자 이름
    private String phone;         // 전화번호
    private String email;               // 이메일
    private String socialNumber;        // 고객 주민 번호
}
