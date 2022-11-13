package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AccountRecord {
    private Long id;
    private String description;
    private Long amount;
    private Long depositId;
    private Long withdrawId;
    private LocalDateTime transferDate;
}
