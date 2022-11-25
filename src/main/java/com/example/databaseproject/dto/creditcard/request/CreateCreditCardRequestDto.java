package com.example.databaseproject.dto.creditcard.request;

import lombok.Value;

@Value
public class CreateCreditCardRequestDto {
    private Long cardNumber;        // 카드 번호
    private String applicationDate; // 카드 신청 일자
    private Long limitAmount;       // 카드 한도 금액
    private String paymentDate;     // 카드 결제 일자
    private String cardType;        // 카드 종류
    private String socialNumber;    // 고객 주민 번호
}
