package com.example.databaseproject.dto.creditcard.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateCreditCardTypeRequestDto {
    private String name;            // 고객 이름
    private String desc;            // 거래 내용
    private Double discountRate;    // 카드 할인율
}
