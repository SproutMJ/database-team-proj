package com.example.databaseproject.repository.jdbc;

import com.example.databaseproject.domain.creditcard.CreditCardType;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreditCardTypeRepository {
    private final JdbcTemplate jdbcTemplate;

    // 카드 타입 생성
    public void createCardType(CreditCardType creditCardType){
        jdbcTemplate.update(
                "insert into CARD_TYPE (DESC, DISCOUNT_RATE, NAME) values (?, ?, ?)",
                creditCardType.getDescription(), creditCardType.getDiscountRate(), creditCardType.getName()
        );
    }
}
