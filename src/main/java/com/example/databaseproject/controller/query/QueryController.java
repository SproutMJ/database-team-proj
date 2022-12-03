package com.example.databaseproject.controller.query;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.account.response.OwnerAccountsDTO;
import com.example.databaseproject.dto.creditcard.request.PayCardDTO;
import com.example.databaseproject.dto.creditcard.response.CreditCardInfoDTO;
import com.example.databaseproject.dto.param.During;
import com.example.databaseproject.service.QueryService;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity showAccountInfo(@PathVariable Long accountNo, During during){
        AccountInfoDTO accountInfoDTO = queryService.showAccountInfo(accountNo, during);
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
        CreditCardInfoDTO cardInfoDTO = queryService.showCardInfo(cardNo);
        return ResponseEntity.ok(cardInfoDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/closest-birthday/{birthday}")
    public ResponseEntity showUsersByClosestBirthday(@PathVariable("birthday") String birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        User user = queryService.closestUser(dateFormat.parse(birthday));
        return ResponseEntity.ok(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/card/pay")
    public ResponseEntity payOfCard(@RequestBody PayCardDTO payCardDTO) throws Exception {
        queryService.payOfCard(payCardDTO);
        return ResponseEntity.ok().build();
    }
}
