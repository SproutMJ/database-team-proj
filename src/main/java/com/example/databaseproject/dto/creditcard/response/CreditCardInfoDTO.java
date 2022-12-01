package com.example.databaseproject.dto.creditcard.response;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCardRecord;
import com.example.databaseproject.domain.creditcard.CreditCardType;
import com.example.databaseproject.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Builder
@Data
public class CreditCardInfoDTO {
    private Long id;
    private String cardNumber;
    private Long payLimit;
    private Date createDate;
    private Long payAmount;
    private List<CreditCardRecord> cardRecords;

    private User user;
    private Account account;
    private CreditCardType cardType;
}
