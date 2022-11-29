package com.example.databaseproject.domain.creditcard;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreditCardType {
    private Long id;                // 고유 번호
    private String name;            // 고객 이름
    private String description;     // 거래 내용
    private double discountRate;   // 카드 할인율
}
