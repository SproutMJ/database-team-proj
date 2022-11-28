package com.example.databaseproject.controller.query;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.account.response.OwnerAccountsDTO;
import com.example.databaseproject.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/query")
@RestController
public class QueryController {
    private final QueryService queryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/accounts/{owner}")
    public ResponseEntity showAccountsByOwner(@PathVariable String owner){
        List<Account> accounts = queryService.showOwnerAccounts(owner);
        return ResponseEntity.ok(accounts);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/accounts/{owner}/{accountNumber}")
    public ResponseEntity showAccountByOwnerAndAccountNumber(@PathVariable String owner, @PathVariable Long accountNumber){
        AccountInfoDTO accountInfoDTO = queryService.showAccountByOwnerAndAccountNumber(owner, accountNumber);
        return ResponseEntity.ok(accountInfoDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/query")
    public ResponseEntity showUsersByQuery(@RequestParam("birthday") String birthday){
        return null;
    }
}
