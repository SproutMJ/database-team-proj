package com.example.databaseproject.controller.account;

import com.example.databaseproject.dto.account.request.CreateAccountTypeRequestDto;
import com.example.databaseproject.service.account.AccountTypeManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccountTypeController {
    private final AccountTypeManageService accountTypeManageService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-accountType")
    public ResponseEntity accounAccountType(@RequestBody CreateAccountTypeRequestDto createAccountTypeRequestDto){
        accountTypeManageService.createAccountType(createAccountTypeRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
