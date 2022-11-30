package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.account.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;

    // 해당 ID에 계좌 정보 찾기
    public Account findById(Long id){
        return jdbcTemplate.queryForObject(
                "select * from Account where ID = ?",
                (rs, row)-> Account.builder()
                        .id(rs.getLong("ID"))
                        .accountId(rs.getString("ACCOUNT_ID"))
                        .userId(rs.getLong("USER"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getBoolean("CARD_APPLY"))
                        .balance(rs.getLong("BALANCE"))
                        .accountType(rs.getLong("TYPE"))
                        .userName(rs.getString("USER_NAME"))
                        .phone(rs.getString("PHONE"))
                        .email(rs.getString("EMAIL"))
                        .socialNumber(rs.getString("SOCIAL_NUMBER"))
                        .build(),
                id);
    }

    public Account findByUser(){
        return null;
    }

    // 해당 계좌 번호에 계좌 정보 찾기
    public Account findByAccountId(Long accountId){
        return jdbcTemplate.queryForObject(
                "select * from Account where ACCOUNT_ID = ?",
                (rs, row)-> Account.builder()
                        .id(rs.getLong("ID"))
                        .accountId(rs.getString("ACCOUNT_ID"))
                        .userId(rs.getLong("USER_ID"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getBoolean("CARD_APPLICATION"))
                        .balance(rs.getLong("BALANCE"))
                        .accountType(rs.getLong("ACCOUNT_TYPE"))
                        .userName(rs.getString("CUSTOMER_NAME"))
                        .phone(rs.getString("PHONE_NUMBER"))
                        .email(rs.getString("EMAIL"))
                        .build(),
                accountId);
    }

    // 해당 고객 이름에 계좌번호 찾기
    public Account findByUsernameAndAccountNumber(String username, Long accountNumber){
        List<Account> accounts = jdbcTemplate.query(
                "select * from Account where USERNAME = ?",
                (rs, row)-> Account.builder()
                        .id(rs.getLong("ID"))
                        .accountId(rs.getString("ACCOUNT_ID"))
                        .userId(rs.getLong("USER"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getBoolean("CARD_APPLY"))
                        .balance(rs.getLong("BALANCE"))
                        .accountType(rs.getLong("TYPE"))
                        .userName(rs.getString("USER_NAME"))
                        .phone(rs.getString("PHONE"))
                        .email(rs.getString("EMAIL"))
                        .socialNumber(rs.getString("SOCIAL_NUMBER"))
                        .build(),
                username);
        return accounts.get(Math.toIntExact(accountNumber));
    }

    // 해당 고객 이름의 모든 계좌 정보 찾기
    public List<Account> findByUsername(String username){
        List<Account> accounts = jdbcTemplate.query(
                "select * from Account where USER_NAME = ?",
                (rs, row)-> Account.builder()
                        .id(rs.getLong("ID"))
                        .accountId(rs.getString("ACCOUNT_ID"))
                        .userId(rs.getLong("USER"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getBoolean("CARD_APPLY"))
                        .balance(rs.getLong("BALANCE"))
                        .accountType(rs.getLong("TYPE"))
                        .userName(rs.getString("user_NAME"))
                        .phone(rs.getString("PHONE"))
                        .email(rs.getString("EMAIL"))
                        .socialNumber(rs.getString("SOCIAL_NUMBER"))
                        .build(),
                username);
        return accounts;
    }

    // 새로운 계좌 생성
    public void createAccount(Account account){
        jdbcTemplate.update(
                "insert into Account (ACCOUNT_ID, USER_ID, CREATE_DATE, CARD_APPLICATION, BALANCE, " +
                        "ACCOUNT_TYPE, CUSTOMER_NAME, PHONE_NUMBER, EMAIL, SOCIAL_NUMBER) " +
                        "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                account.getAccountId(), account.getUserId(), account.getCreateDate(), account.isCardApply(),
                account.getBalance(), account.getAccountType(), account.getUserName(), account.getPhone(),
                account.getEmail(), account.getSocialNumber()
        );
    }

    // 계좌 잔금 업데이트
    public void updateBalance(Long accountId, Long balance){
        jdbcTemplate.update(
                "update account set BALANCE=BALANCE+? where ID=?",
                balance, accountId
        );
    }
    
    //// TODO: 2022-10-12 추후 추가
}
