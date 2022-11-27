package com.example.databaseproject.controller.creditcard;

import com.example.databaseproject.dto.creditcard.request.CreateCreditCardRequestDto;
import com.example.databaseproject.service.creditcard.CreditCardManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CreditCardController {
    private final CreditCardManageService creditCardManageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-card")
    public ResponseEntity creditCardCreate(@RequestBody CreateCreditCardRequestDto createCreditCardRequestDto){
        creditCardManageService.createCreditCard(createCreditCardRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
