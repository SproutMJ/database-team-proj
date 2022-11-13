package com.example.databaseproject.controller.account;

import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.dto.account.request.RemittanceDto;
import com.example.databaseproject.dto.auth.request.UserRegisterRequestDto;
import com.example.databaseproject.service.account.AccountManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccountController {
    private final AccountManageService accountManageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-account")
    public ResponseEntity accountCreate(@RequestBody CreateAccountRequestDto createAccountRequestDto){
        accountManageService.createAccount(createAccountRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/transfer")
    public ResponseEntity remittance(@RequestBody RemittanceDto remittanceDto){
        accountManageService.remittance(remittanceDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
