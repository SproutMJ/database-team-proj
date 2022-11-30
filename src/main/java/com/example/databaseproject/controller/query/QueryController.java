package com.example.databaseproject.controller.query;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.account.response.OwnerAccountsDTO;
import com.example.databaseproject.service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/query")
@RestController
public class QueryController {
    private final QueryService queryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{name}")
    public ResponseEntity showUser(@PathVariable String name){
        List<User> users = queryService.showUsers(name);
        return ResponseEntity.ok(users);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userNo}/accounts")
    public ResponseEntity showUserHaveAccounts(@PathVariable Long userNo){
        List<Account> accounts = queryService.showUserHaveAccounts(userNo);
        return ResponseEntity.ok(accounts);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/accountInfo/{accountNo}")
    public ResponseEntity showAccountInfo(@PathVariable Long accountNo){
        AccountInfoDTO accountInfoDTO = queryService.showAccountInfo(accountNo);
        return ResponseEntity.ok(accountInfoDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userNo}/cards")
    public ResponseEntity showUserHaveCards(@PathVariable Long userNo){
        List<CreditCard> creditCards = queryService.showUserHaveCards(userNo);
        return ResponseEntity.ok(creditCards);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/cardInfo/{cardNo}")
    public ResponseEntity showCardInfo(@PathVariable Long cardNo){
        return null;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/query")
    public ResponseEntity showUsersByQuery(@RequestParam("birthday") Date birthday){
        return null;
    }
}
