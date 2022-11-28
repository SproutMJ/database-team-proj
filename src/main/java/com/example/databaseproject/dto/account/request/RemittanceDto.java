package com.example.databaseproject.dto.account.request;

import lombok.Data;
import lombok.Value;

@Data
public class RemittanceDto {
    private Long withdraw;
    private Long deposit;
    private Long amount;
    private String description;
}
