package com.example.databaseproject.repository.jdbc;

//import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.domain.creditcard.CreditCardRecord;
import com.example.databaseproject.domain.creditcard.CreditCardType;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.creditcard.response.CreditCardInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<CreditCard> findByUserId(Long userId){
        List<CreditCard> creditCards = jdbcTemplate.query(
                "select * from user, card, card_type\n" +
                        "where card.ID = ? and\n" +
                        "        user.id = card.user and\n" +
                        "        card_type.ID = card.card_type\n" +
                        "order by card.create_date"
                ,(rs,row)->CreditCard.builder()
                        .id(rs.getLong("card.ID"))
                        .userId(rs.getLong("user"))
                        .account(rs.getLong("account"))
                        .cardType(rs.getString("card_type.name"))
                        .payLimit(rs.getLong("pay_limit"))
                        .createDate(rs.getDate("create_date"))
                        .payAmount(rs.getLong("pay_amount"))
                        .build()
                , userId
        );
        return creditCards;
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

    public CreditCardInfoDTO findInfoById(Long cardNo){
        CreditCardInfoDTO infoDTO = jdbcTemplate.queryForObject(
                "select * from user, card, card_type, account\n" +
                        "where card.ID = ? and\n" +
                        "        user.id = card.user and\n" +
                        "        card_type.ID = card.card_type and\n" +
                        "        account.ID = card.account\n" +
                        "order by card.create_date"
                , (rs, row)->CreditCardInfoDTO.builder()
                        .id(rs.getLong("card.id"))
                        .cardNumber(rs.getString("card_number"))
                        .payLimit(rs.getLong("pay_limit"))
                        .createDate(rs.getDate("card.create_date"))
                        .payAmount(rs.getLong("pay_amount"))
                        .user(User.builder()
                                .id(rs.getLong("user.id"))
                                .name(rs.getString("user.name"))
                                .address(rs.getString("address"))
                                .email(rs.getString("email"))
                                .phone(rs.getString("phone"))
                                .job(rs.getString("job"))
                                .birthday(rs.getDate("birthday"))
                                .socialNumber(rs.getString("social_number"))
                                .build())
                        .account(Account.builder()
                                .accountId(rs.getString("account_id"))
                                .userId(rs.getLong("user.id"))
                                .createDate(rs.getDate("account.create_date"))
                                .cardApply(rs.getLong("card_apply"))
                                .balance(rs.getLong("balance"))
                                .accountType(rs.getLong("type"))
                                .userName(rs.getString("user.name"))
                                .phone(rs.getString("phone"))
                                .email(rs.getString("email"))
                                .id(rs.getLong("account.id"))
                                .socialNumber(rs.getString("social_number"))
                                .build())
                        .cardType(CreditCardType.builder()
                                .id(rs.getLong("card_type.id"))
                                .name(rs.getString("card_type.name"))
                                .description(rs.getString("desc"))
                                .discountRate(rs.getDouble("discount_rate"))
                                .build())
                        .build()
                ,cardNo
        );

        List<CreditCardRecord> creditCardRecords = jdbcTemplate.query(
                "select card_record.* from card, card_record\n" +
                        "   where card.ID = card_record.card and\n" +
                        "   card.ID = ?\n" +
                        "   order by card_record.pay_date"
                , (rs, row)->CreditCardRecord.builder()
                        .id(rs.getLong("id"))
                        .amount(rs.getLong("amount"))
                        .cardId(rs.getLong("card"))
                        .description(rs.getString("desc"))
                        .date(rs.getTimestamp("pay_date").toLocalDateTime())
                        .build()
                ,cardNo
        );

        infoDTO.setCardRecords(creditCardRecords);
        return infoDTO;
    }
}
