package com.example.databaseproject.dto.creditcard.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateCreditCardRequestDto {
    private String socialNumber;    // 고객 주민번호
    private String accountNumber;   // 고객 계좌번호
    private String cardType;        // 고객 카드타입
    private Long payLimit;          // 카드 한도
}
