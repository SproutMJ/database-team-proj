package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.account.AccountRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountRecordRepository {
    private final JdbcTemplate jdbcTemplate;


    public void insertRecord(AccountRecord accountRecord){
        jdbcTemplate.update(
                "insert into account_record (description, amount, deposit, withdraw, transfer_date) values (?, ?, ?, ?, ?)",
                accountRecord.getDescription(), accountRecord.getAmount(), accountRecord.getDepositId(), accountRecord.getWithdrawId(), accountRecord.getTransferDate()
        );
    }
}
