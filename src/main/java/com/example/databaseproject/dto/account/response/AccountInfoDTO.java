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
    private String accountId;
    private Date createDate;
    private boolean cardRegistered;
    private Long balance;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String socialNumber;
    private AccountTypeDTO accountTypeDTO;
    private List<AccountRecord> accountRecords;

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
