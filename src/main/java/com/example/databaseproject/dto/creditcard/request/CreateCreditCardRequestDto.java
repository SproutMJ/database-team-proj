package com.example.databaseproject.dto.creditcard.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateCreditCardRequestDto {
    private String socialNumber;
    private String accountNumber;
    private String cardType;
    private Long payLimit;
}
