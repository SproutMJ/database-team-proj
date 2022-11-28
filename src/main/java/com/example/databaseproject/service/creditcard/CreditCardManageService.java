package com.example.databaseproject.service.creditcard;


import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.dto.creditcard.request.CreateCreditCardRequestDto;
import com.example.databaseproject.repository.jdbc.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class CreditCardManageService {
    private final CreditCardRepository creditCardRepository;

    public void createCreditCard(CreateCreditCardRequestDto createCreditCardRequestDto){
        creditCardRepository.createCreditCard(CreditCard.builder()
                .cardId("1234-5678")
                .createDate(Date.from(Instant.now()))
                .payLimit(createCreditCardRequestDto.getPayLimit())
                .payAmount(0L)
                .cardType(createCreditCardRequestDto.getCardType())
                .build()
        );
    }
}
