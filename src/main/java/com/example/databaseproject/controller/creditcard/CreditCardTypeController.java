package com.example.databaseproject.controller.creditcard;

import com.example.databaseproject.dto.creditcard.request.CreateCreditCardRequestDto;
import com.example.databaseproject.dto.creditcard.request.CreateCreditCardTypeRequestDto;
import com.example.databaseproject.service.creditcard.CreditCardTypeManegeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CreditCardTypeController {

    private final CreditCardTypeManegeService createCreditCard;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-cardtype")
    public ResponseEntity creditCardCreate(@RequestBody CreateCreditCardTypeRequestDto cardTypeRequestDto){
        createCreditCard.createCardType(cardTypeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
