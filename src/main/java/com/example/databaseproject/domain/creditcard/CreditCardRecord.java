package com.example.databaseproject.domain.creditcard;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class CreditCardRecord {
    private Long id;                    // 고유 번호
    private Long amount;                // 거래 금액
    private Long cardId;                // 카드 ID
    private String description;         // 거래 내용
    private LocalDateTime transferDate; // 사용 날짜
}
