package com.example.databaseproject.dto.creditcard.request;

import lombok.Data;

@Data
public class PayCardDTO {
    private Long amount;
    private String cardNumber;
    private String desc;
}
