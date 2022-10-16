package com.example.databaseproject.service.account;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.repository.jdbc.AccountRepository;
import com.example.databaseproject.repository.jdbc.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AccountManageService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;


    public void createAccount(CreateAccountRequestDto createAccountRequestDto){
        accountRepository.createAccount(Account.builder()
                .cardRegistered(createAccountRequestDto.isCardRegistered())
                .accountType(createAccountRequestDto.getAccountType())
                .customerName(createAccountRequestDto.getCustomerName())
                .phoneNumber(createAccountRequestDto.getPhoneNumber())
                .email(createAccountRequestDto.getEmail())
                .socialNumber(createAccountRequestDto.getSocialNumber())
                .accountId(createAccountRequestDto.getAccountType().toString()+Instant.now().toString())
                .userId(000L)
                .createDate(Date.from(Instant.now()))
                .balance(0L)
                .build()
        );
    }
}
