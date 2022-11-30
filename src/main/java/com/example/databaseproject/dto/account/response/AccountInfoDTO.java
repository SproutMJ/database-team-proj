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
    private Long id;
    private String accountId;
    private Date createDate;
    private Long cardApply;
    private Long balance;
    private String userName;
    private String phone;
    private String email;
    private String socialNumber;
    private String typeName;
    private Double interestRate;
    private String typeDesc;
    private List<AccountRecord> accountRecords;
}
