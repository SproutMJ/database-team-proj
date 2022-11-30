package com.example.databaseproject.service;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.account.response.OwnerAccountsDTO;
import com.example.databaseproject.repository.jdbc.AccountRepository;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public List<User> showUsers(String name){
        List<User> users = userRepository.findByName(name);
        return users;
    }

    public List<Account> showUserHaveAccounts(Long userNo){
        List<Account> accounts = accountRepository.findByUserId(userNo);
        return accounts;
    }

    public AccountInfoDTO showAccountByOwnerAndAccountNumber(String owner, Long accountNumber){
        Account account = accountRepository.findByUsernameAndAccountNumber(owner, accountNumber);
//        AccountInfoDTO accountInfoDTO = AccountInfoDTO.builder()
//                .accountId(account.getAccountId())
//                .createDate(account.getCreateDate())
//                .cardRegistered(account.isCardRegistered())
//                .balance(account.getBalance())
//                .customerName(account.getCustomerName())
//                .phoneNumber(account.getPhoneNumber())
//                .email(account.getEmail())
//                .socialNumber(account.getSocialNumber())
//                .accountTypeDTO(null)
//                .accountRecords(null)
//                .build();
        return null;
    }

    public List<User> showUsers(Map<String, String> queryType){
        return null;
    }

    public AccountInfoDTO showAccountInfo(Long accountNo) {
        return accountRepository.infoFindById(accountNo);
    }
}
