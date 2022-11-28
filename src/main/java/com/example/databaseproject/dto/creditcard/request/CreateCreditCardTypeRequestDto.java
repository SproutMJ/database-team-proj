package com.example.databaseproject.dto.creditcard.request;

import lombok.Data;
import lombok.Value;

@Data
public class CreateCreditCardTypeRequestDto {
    private String name;
    private String desc;
    private Double discountRate;
}
