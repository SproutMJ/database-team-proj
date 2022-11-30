package com.example.databaseproject.dto.account.response;

import com.example.databaseproject.domain.account.AccountRecord;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class AccountInfoDTO {
    private String accountId;                       // 계좌번호
    private Date createDate;                        // 계좌 발급일
    private boolean cardRegistered;                 // 카드 신청 유무
    private Long balance;                           // 계좌 잔고
    private String customerName;                    // 고객 이름
    private String phoneNumber;                     // 고객 전화번호
    private String email;                           // 고객 이메일
    private String socialNumber;                    // 고객 주민번호
    private AccountTypeDTO accountTypeDTO;          // 고객 계좌 타입
    private List<AccountRecord> accountRecords;     // 고객 계좌 기록

    public class AccountRecordDTO {
        private String description;
        private Long amount;
        private Long depositId;
        private Long withdrawId;
        private LocalDateTime transferDate;
    }

    public class AccountTypeDTO {
        private String description;
        private double interestRate;
        private String name;
    }
}
