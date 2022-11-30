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

    // 계좌 거래 기록 생성
    public void insertAccountRecord(AccountRecord accountRecord){
        jdbcTemplate.update(
                "insert into ACCOUNT_RECORD (DESC, AMOUNT, DEPOSIT_ACCOUNT, WITHDRAW_ACCOUNT, DATE) " +
                        "values (?, ?, ?, ?, ?)",
                accountRecord.getDescription(), accountRecord.getAmount(), accountRecord.getDepositId(),
                accountRecord.getWithdrawId(), accountRecord.getTransferDate()
        );
    }
}
