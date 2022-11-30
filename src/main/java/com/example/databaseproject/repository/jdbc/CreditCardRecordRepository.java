package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.creditcard.CreditCardRecord;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreditCardRecordRepository {
    private final JdbcTemplate jdbcTemplate;

    // 카드 거래 기록 생성
    public void insertCardRecord(CreditCardRecord creditCardRecord){
        jdbcTemplate.update(
                "insert into CARD_RECORD (DESC, AMOUNT, CARD, PAY_DATE) " +
                        "values (?, ?, ?, ?)",
                creditCardRecord.getDescription(), creditCardRecord.getAmount(),
                creditCardRecord.getCardId(), creditCardRecord.getTransferDate()
        );
    }
}
