package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class AccountRecord {
    private Long id;                        // 예금 계좌 ID
    private String description;             // 예금 내용
    private Long amount;                    // 거래 금액 (?)
    private Long depositId;                 // 입금 계좌 ID(예금 구분?)
    private Long withdrawId;                // 출금 계좌 ID(예금 구분?)
    private LocalDateTime transferDate;     // 입출금 날짜

}
