package com.example.databaseproject.repository.jdbc;

//import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCard;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CreditCardRepository {
    private final JdbcTemplate jdbcTemplate;

    // 카드 번호에 해당하는 카드 모든 정보 찾기
    public CreditCard findById(Long cardNumber){
//        return jdbcTemplate.queryForObject(
//          "select * from CreditCard where CARD_NUMBER = ?",
//                (rs, rowNum) -> CreditCard.builder().id(rs.getLong("ID"))
//                        .createDate(rs.getDate("APPLICATION_DATE"))
//                        .payLimit(rs.getLong("LIMIT_AMOUNT"))
//                        .cardType(rs.getString("CARD_TYPE"))
//        );
        return null;
    }

    // 새로운 카드 발급
    public void createCreditCard(CreditCard creditCard){
        jdbcTemplate.update(
                "insert into CreditCard (ID, CARD_NUMBER, " +
                        "APPLICATION_DATE, LIMIT_AMOUNT, PAYMENT_DATE, " +
                        "CARD_TYPE, SOCIAL_NUMBER) VALUES (?, ?, ?, ?, ?, ?)",
                creditCard.getCardId(), creditCard.getCreateDate(), creditCard.getPayLimit()
        );
    }

    // 카드에 연결되어 있는 계좌 찾기
    public CreditCard findLinkedAccount(Long cardNumber){
        return jdbcTemplate.queryForObject(
                "select ID from CreditCard where CARD_NUMBER = ?",
                (rs, rowNum) -> CreditCard.builder().id(rs.getLong("ID")).build(), cardNumber
        );
    }

    // 카드 사용 한도 찾기
    public CreditCard findLimitAmount(Long cardNumber){
        return jdbcTemplate.queryForObject(
                "select LIMIT_AMOUNT from CreditCard where CARD_NUMBER = ?",
                (rs, rowNum) -> CreditCard.builder().payLimit(rs.getLong("LIMIT_AMOUNT")).build(), cardNumber
        );
    }

    // 총 카드 사용 금액
//    public CreditCard findAmountUsed(){

//    }
}
