package com.example.databaseproject.domain.creditcard;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class CreditCard {
    private String cardId;          // 카드번호
    private Long userId;            // 유저 ID
    private Long account;           // 등록된 계좌
    private String cardType;        // 카드 종류
    private Long payLimit;          // 카드 한도 금액
    private Date createDate;        // 카드 발급일
    private Long payAmount;         // 카드 사용 금액
    private Long id;                // 고유 번호

}
