package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.account.AccountRecord;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.param.During;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class AccountRepository {
    private final JdbcTemplate jdbcTemplate;
    public Account findById(Long id){
        return jdbcTemplate.queryForObject(
                "select * from Account where ID = ?",
                (rs, row)-> Account.builder().id(rs.getLong("ID")).accountId(rs.getString("ACCOUNT_ID")).userId(rs.getLong("USER_ID")).createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("CARD_APPLICATION")).balance(rs.getLong("BALANCE")).accountType(rs.getLong("ACCOUNT_TYPE")).userName(rs.getString("CUSTOMER_NAME"))
                        .phone(rs.getString("PHONE_NUMBER")).email(rs.getString("EMAIL")).build(),
                id);
    }

    public List<Account> findByUserId(Long id){
        return jdbcTemplate.query(
                "select * from user, account\n" +
                        "where user.id = ? and\n" +
                        "        user.id = account.user\n" +
                        "order by account.create_date",
                (rs, row)-> Account.builder()
                        .id(rs.getLong("account.ID"))
                        .accountId(rs.getString("ACCOUNT_ID"))
                        .userId(rs.getLong("USER"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("CARD_APPLY"))
                        .balance(rs.getLong("BALANCE"))
                        .accountType(rs.getLong("TYPE"))
                        .userName(rs.getString("NAME"))
                        .phone(rs.getString("PHONE"))
                        .email(rs.getString("EMAIL"))
                        .socialNumber(rs.getString("social_number"))
                        .build(),
                id);
    }

    public Account findByUser(){
        return null;
    }

    public Account findByAccountId(Long accountId){
        return jdbcTemplate.queryForObject(
                "select * from Account where ACCOUNT_ID = ?",
                (rs, row)-> Account.builder().id(rs.getLong("ID")).accountId(rs.getString("ACCOUNT_ID")).userId(rs.getLong("USER_ID")).createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("CARD_APPLICATION")).balance(rs.getLong("BALANCE")).accountType(rs.getLong("ACCOUNT_TYPE")).userName(rs.getString("CUSTOMER_NAME"))
                        .phone(rs.getString("PHONE_NUMBER")).email(rs.getString("EMAIL")).build(),
                accountId);
    }

    public Account findByAccountNumber(String accountNumber){
        return jdbcTemplate.queryForObject(
                "select * from Account where ACCOUNT_ID = ?",
                (rs, row)-> Account.builder()
                        .id(rs.getLong("ID"))
                        .accountId(rs.getString("ACCOUNT_ID"))
                        .userId(rs.getLong("USER"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("CARD_APPLY"))
                        .balance(rs.getLong("BALANCE"))
                        .accountType(rs.getLong("TYPE"))
                        .build(),
                accountNumber);
    }

    public Account findByUsernameAndAccountNumber(String username, Long accountNumber){
        List<Account> accounts = jdbcTemplate.query(
                "select * from Account where USERNAME = ?",
                (rs, row)-> Account.builder().id(rs.getLong("ID")).accountId(rs.getString("ACCOUNT_ID")).userId(rs.getLong("USER_ID")).createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("CARD_APPLICATION")).balance(rs.getLong("BALANCE")).accountType(rs.getLong("ACCOUNT_TYPE")).userName(rs.getString("CUSTOMER_NAME"))
                        .phone(rs.getString("PHONE_NUMBER")).email(rs.getString("EMAIL")).build(),
                username);
        return accounts.get(Math.toIntExact(accountNumber));
    }

    public List<Account> findByUsername(String username){
        List<Account> accounts = jdbcTemplate.query(
                "select * from Account where user_NAME = ?",
                (rs, row)-> Account.builder().id(rs.getLong("ID")).accountId(rs.getString("ACCOUNT_ID")).userId(rs.getLong("USER")).createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("CARD_APPLY")).balance(rs.getLong("BALANCE")).accountType(rs.getLong("TYPE")).userName(rs.getString("user_NAME"))
                        .phone(rs.getString("PHONE")).email(rs.getString("EMAIL")).socialNumber(rs.getString("SOCIAL_NUMBER")).build(),
                username);
        return accounts;
    }

    public void createAccount(Account account){
        jdbcTemplate.update(
                "insert into Account (ACCOUNT_ID, USER_ID, CREATE_DATE, CARD_APPLICATION, BALANCE, ACCOUNT_TYPE, CUSTOMER_NAME, PHONE_NUMBER, EMAIL, SOCIAL_NUMBER) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                account.getAccountId(), account.getUserId(), account.getCreateDate(), account.getCardApply(), account.getBalance(), account.getAccountType(), account.getUserName(), account.getPhone(), account.getEmail()
        );
    }

    public void updateBalance(Long accountId, Long balance){
        jdbcTemplate.update(
                "update account set BALANCE=BALANCE+? where ID=?",
                balance, accountId
                );
    }
    
    //// TODO: 2022-10-12 추후 추가

    public AccountInfoDTO infoFindById (Long accountNo){
        AccountInfoDTO infoDTO = jdbcTemplate.queryForObject(
                "select * from user, account, account_type\n" +
                        "where account.ID = ? and\n" +
                        "        user.id = account.user and\n" +
                        "        account_type.ID = account.type\n" +
                        "order by account.create_date"
                , (rs, row)->AccountInfoDTO.builder()
                        .id(rs.getLong("account.ID"))
                        .accountId(rs.getString("account_id"))
                        .createDate(rs.getDate("CREATE_DATE"))
                        .cardApply(rs.getLong("card_apply"))
                        .balance(rs.getLong("BALANCE"))
                        .userName(rs.getString("user.name"))
                        .phone(rs.getString("phone"))
                        .email(rs.getString("email"))
                        .socialNumber("social_number")
                        .typeName(rs.getString("account_type.name"))
                        .interestRate(rs.getDouble("INTEREST_RATE"))
                        .typeDesc(rs.getString("DESCRIPTION"))
                        .build()
                , accountNo
        );

        List<AccountRecord> records = jdbcTemplate.query(
                "select account_record.* from account, account_record\n" +
                        "where (account.ID = account_record.deposit_account or\n" +
                        "      account.ID = account_record.withdraw_account) and\n" +
                        "      account.ID = ?\n" +
                        "order by account_record.date;",
                (rs, row)->AccountRecord.builder()
                        .state(((infoDTO.getId() == rs.getLong("deposit_account"))?"입급":"출금"))
                        .amount(rs.getLong("amount"))
                        .description(rs.getString("desc"))
                        .transferDate(rs.getTimestamp("date").toLocalDateTime())
                        .build()
                ,accountNo
        );
        infoDTO.setAccountRecords(records);
        return infoDTO;
    }
}
