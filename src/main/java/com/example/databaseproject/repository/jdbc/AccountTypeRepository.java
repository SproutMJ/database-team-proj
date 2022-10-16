package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.account.AccountType;
import com.example.databaseproject.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    public void createAccountType(AccountType accountType) {
        jdbcTemplate.update(
                "insert into ACCOUNT_TYPE (DESCRIPTION, INTEREST_RATE, NAME) values (?, ?, ?)",
                accountType.getDescription(), accountType.getInterestRate(), accountType.getName()
        );
    }
}
