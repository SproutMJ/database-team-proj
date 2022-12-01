package com.example.databaseproject.service;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.account.response.OwnerAccountsDTO;
import com.example.databaseproject.dto.creditcard.response.CreditCardInfoDTO;
import com.example.databaseproject.repository.jdbc.AccountRepository;
import com.example.databaseproject.repository.jdbc.CreditCardRepository;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CreditCardRepository creditCardRepository;

    public List<User> showUsers(String name){
        List<User> users = userRepository.findByName(name);
        return users;
    }

    public List<Account> showUserHaveAccounts(Long userNo){
        List<Account> accounts = accountRepository.findByUserId(userNo);
        return accounts;
    }

    public List<CreditCard> showUserHaveCards(Long userNo){
        List<CreditCard> creditCards = creditCardRepository.findByUserId(userNo);
        return creditCards;
    }

    public List<User> showUsers(Map<String, String> queryType){
        return null;
    }

    public AccountInfoDTO showAccountInfo(Long accountNo) {
        return accountRepository.infoFindById(accountNo);
    }

    public CreditCardInfoDTO showCardInfo(Long cardNo){
        return creditCardRepository.findInfoById(cardNo);
    }

    public User closestUser(Date birthday){
        User user = userRepository.findClosestBirthdayUser(birthday);
        return user;
    }
}
