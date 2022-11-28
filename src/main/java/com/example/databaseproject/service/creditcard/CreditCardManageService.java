package com.example.databaseproject.service.creditcard;


import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.dto.creditcard.request.CreateCreditCardRequestDto;
import com.example.databaseproject.repository.jdbc.CreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditCardManageService {
    private final CreditCardRepository creditCardRepository;

    public void createCreditCard(CreateCreditCardRequestDto createCreditCardRequestDto){
        creditCardRepository.createCreditCard(CreditCard.builder()
                .cardNumber(createCreditCardRequestDto.getCardNumber())
                .applicationDate(createCreditCardRequestDto.getApplicationDate())
                .limitAmount(createCreditCardRequestDto.getLimitAmount())
                .paymentDate(createCreditCardRequestDto.getPaymentDate())
                .cardType(createCreditCardRequestDto.getCardType())
                .socialNumber(createCreditCardRequestDto.getSocialNumber())
                .build()
        );
    }
}
