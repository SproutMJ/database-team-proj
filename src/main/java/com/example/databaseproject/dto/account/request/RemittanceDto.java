package com.example.databaseproject.dto.account.request;

import lombok.Data;
import lombok.Value;

@Data
public class RemittanceDto {
    private Long withdraw;          // 출금
    private Long deposit;           // 예금
    private Long amount;            // 잔금
    private String description;     // 거래 내용
}
