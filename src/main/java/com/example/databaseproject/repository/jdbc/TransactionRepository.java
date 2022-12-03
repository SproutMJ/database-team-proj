package com.example.databaseproject.repository.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TransactionRepository {
    private final JdbcTemplate jdbcTemplate;

    public void payCardAndAccount(Long amount, Long cardId, Long accountId, String desc) {
        jdbcTemplate.update("update card\n" +
                        "set pay_amount = pay_amount + ?\n" +
                        "where id = ?"
                , amount, cardId
        );
        jdbcTemplate.update("update account\n" +
                        "set balance = balance - ?\n" +
                        "where ID = ?"
                , amount, accountId);

        jdbcTemplate.update("insert into card_record(amount, card, card_record.desc, pay_date)\n" +
                        "values (?, ?, ?, now())"
                , amount, cardId, "[카드] " + desc);

        jdbcTemplate.update("insert into account_record(deposit_account, withdraw_account, amount, account_record.desc, date)\n" +
                        "values (null, ?, ?, ?, now())"
                , accountId, amount, "[카드] " + desc);
    }

    public void accountTransfer(Long withdraw, Long deposit, Long amount, String description) {
        jdbcTemplate.update("update account\n" +
                        "set balance = balance - ?\n" +
                        "where id = ?"
                , amount, withdraw
        );
        jdbcTemplate.update("update account\n" +
                        "set balance = balance + ?\n" +
                        "where id = ?"
                , amount, deposit
        );

        jdbcTemplate.update("insert into account_record(deposit_account, withdraw_account, amount, account_record.desc, date)\n" +
                        "values (?, ?, ?, ?, now())"
                , deposit, withdraw, amount, "[" + withdraw + " -> " + deposit + "]" + description);
    }
}
