package com.example.databaseproject.domain.card;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CreditCardRecord {
    private Long id;            // 고유 번호
    private Long amount;        // 거래 금액
    private Long cardId;        // 사용 카드
    private String description; // 거래 내용
    private LocalDateTime date; // 사용 날짜
}
