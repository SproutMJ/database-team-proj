package com.example.databaseproject.domain.account;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class Account {
    private Long id;
    private String accountId;
    private Long userId;
    private Date createDate;
    private boolean cardRegistered;
    private Long balance;
    private Long accountType;
    private String customerName;
    private String phoneNumber;
    private String email;
    private String socialNumber;
}
