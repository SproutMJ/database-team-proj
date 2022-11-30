package com.example.databaseproject.service.creditcard;

import com.example.databaseproject.domain.creditcard.CreditCardType;
import com.example.databaseproject.dto.creditcard.request.CreateCreditCardTypeRequestDto;
import com.example.databaseproject.repository.jdbc.CreditCardTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class CreditCardTypeManegeService {
    private final CreditCardTypeRepository creditCardTypeRepository;
    public void createCardType(CreateCreditCardTypeRequestDto dto) {
        creditCardTypeRepository.createCardType(CreditCardType.builder()
                .name(dto.getName())
                .description(dto.getDesc())
                .discountRate(dto.getDiscountRate())
                .build()
        );
    }
}
