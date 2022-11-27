package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Account {
    private Long id;                // 예금 계좌 ID(?)
    private String accountId;       // ID (?)
    private Long userId;            // 사용자 ID (?)
    private Date createDate;        // 예금 개설 일자
    private boolean cardRegistered; // 카드 신청 여부
    private Long balance;           // 예금 잔고
    private Long accountType;       // 예금 계좌 종류
    private String customerName;    // 예금자 이름
    private String phoneNumber;     // 전화번호
    private String email;           // 이메일
    private String socialNumber;    // 고객 주민 번호
}
