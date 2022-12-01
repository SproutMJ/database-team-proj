package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Account {
    private String accountId;           // 계좌번호
    private Long userId;                // 유저 ID
    private Date createDate;            // 계좌 발급일
    private Long cardApply;          // 카드 신청 여부
    private Long balance;               // 계좌 잔고
    private Long accountType;           // 계좌 타입
    private String userName;        // 계좌 고객 이름
    private String phone;         // 고객 전화번호
    private String email;               // 고객 이메일
    private Long id;                    // 고유 번호
    private String socialNumber;
}
