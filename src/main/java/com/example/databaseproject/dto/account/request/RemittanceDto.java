package com.example.databaseproject.dto.account.request;

import lombok.Data;
import lombok.Value;

@Data
public class RemittanceDto {
    private String withdraw;
    private String deposit;
    private Long amount;
    private String description;
}
