package com.example.databaseproject.dto.account.request;

import lombok.Value;

@Value
public class RemittanceDto {
    private Long withdraw;
    private Long deposit;
    private Long amount;
    private String description;
}
