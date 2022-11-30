package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AccountRecord {
    private Long id;                        // 고유 번호
    private String state;              // 입금 계좌
    private Long depositId;
    private Long withdrawId;
    private Long amount;                    // 거래 금액
    private String description;             // 거래 내용
    private LocalDateTime transferDate;     // 입출금 날짜

}
