package com.example.databaseproject.service.account;

import com.example.databaseproject.domain.account.AccountType;
import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.dto.account.request.CreateAccountTypeRequestDto;
import com.example.databaseproject.repository.jdbc.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountTypeManageService {
    private final AccountTypeRepository accountTypeRepository;


    public void createAccountType(CreateAccountTypeRequestDto dto){
        accountTypeRepository.createAccountType(AccountType.builder().description(dto.getDescription()).interestRate(dto.getInterestRate()).name(dto.getName()).build());
    }
}
