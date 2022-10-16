package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    public Account findById(Long id){
        return jdbcTemplate.queryForObject(
                "select * from Account where ID = ?",
                (rs, row)-> Account.builder().id(rs.getLong("ID")).accountId(rs.getString("ACCOUNT_ID")).userId(rs.getLong("USER_ID")).createDate(rs.getDate("CREATE_DATE"))
                        .cardRegistered(rs.getBoolean("CARD_APPLICATION")).balance(rs.getLong("BALANCE")).accountType(rs.getLong("ACCOUNT_TYPE")).customerName(rs.getString("CUSTOMER_NAME"))
                        .phoneNumber(rs.getString("PHONE_NUMBER")).email(rs.getString("EMAIL")).socialNumber(rs.getString("SOCIAL_NUMBER")).build(),
                id);
    }

    public Account findByUser(){
        return null;
    }

    public Account findByAccountId(Long accountId){
        return jdbcTemplate.queryForObject(
                "select * from Account where ACCOUNT_ID = ?",
                (rs, row)-> Account.builder().id(rs.getLong("ID")).accountId(rs.getString("ACCOUNT_ID")).userId(rs.getLong("USER_ID")).createDate(rs.getDate("CREATE_DATE"))
                        .cardRegistered(rs.getBoolean("CARD_APPLICATION")).balance(rs.getLong("BALANCE")).accountType(rs.getLong("ACCOUNT_TYPE")).customerName(rs.getString("CUSTOMER_NAME"))
                        .phoneNumber(rs.getString("PHONE_NUMBER")).email(rs.getString("EMAIL")).socialNumber(rs.getString("SOCIAL_NUMBER")).build(),
                accountId);
    }

    public void createAccount(Account account){
        jdbcTemplate.update(
                "insert into USER (ACCOUNT_ID, USER_ID, CREATE_DATE, CARD_APPLICATION, BALANCE, ACCOUNT_TYPE, CUSTOMER_NAME, PHONE_NUMBER, EMAIL, SOCIAL_NUMBER) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                account.getAccountId(), account.getUserId(), account.getCreateDate(), account.isCardRegistered(), account.getBalance(), account.getAccountType(), account.getCustomerName(), account.getPhoneNumber(), account.getEmail(), account.getSocialNumber()
        );
    }
    
    //// TODO: 2022-10-12 추후 추가
}
