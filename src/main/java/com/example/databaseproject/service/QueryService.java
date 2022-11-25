package com.example.databaseproject.service;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.account.response.OwnerAccountsDTO;
import com.example.databaseproject.repository.jdbc.AccountRepository;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QueryService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public OwnerAccountsDTO showOwnerAccounts(String ownerName){
        List<Account> accounts = accountRepository.findByUsername(ownerName);
        OwnerAccountsDTO ownerAccountsDTO = OwnerAccountsDTO.builder()
                .ownerName(ownerName)
                .accountInfoDTOs(accounts.stream().map((a)->{
                    return AccountInfoDTO.builder()
                            .accountId(a.getAccountId())
                            .createDate(a.getCreateDate())
                            .cardRegistered(a.isCardRegistered())
                            .balance(a.getBalance())
                            .customerName(ownerName)
                            .phoneNumber(a.getPhoneNumber())
                            .email(a.getEmail())
                            .socialNumber(a.getSocialNumber())
                            .accountTypeDTO(null)
                            .accountRecords(null)
                            .build();
                }).collect(Collectors.toList()))
                .build();
        return ownerAccountsDTO;
    }

    public AccountInfoDTO showAccountByOwnerAndAccountNumber(String owner, Long accountNumber){
        Account account = accountRepository.findByUsernameAndAccountNumber(owner, accountNumber);
        AccountInfoDTO accountInfoDTO = AccountInfoDTO.builder()
                .accountId(account.getAccountId())
                .createDate(account.getCreateDate())
                .cardRegistered(account.isCardRegistered())
                .balance(account.getBalance())
                .customerName(account.getCustomerName())
                .phoneNumber(account.getPhoneNumber())
                .email(account.getEmail())
                .socialNumber(account.getSocialNumber())
                .accountTypeDTO(null)
                .accountRecords(null)
                .build();
        return accountInfoDTO;
    }

    public List<User> showUsers(Map<String, String> queryType){

    }
}
