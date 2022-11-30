package com.example.databaseproject.service.account;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.account.AccountRecord;
import com.example.databaseproject.dto.account.request.CreateAccountRequestDto;
import com.example.databaseproject.dto.account.request.RemittanceDto;
import com.example.databaseproject.repository.jdbc.AccountRecordRepository;
import com.example.databaseproject.repository.jdbc.AccountRepository;
import com.example.databaseproject.repository.jdbc.AccountTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AccountManageService {

    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountRecordRepository accountRecordRepository;

    public void createAccount(CreateAccountRequestDto createAccountRequestDto){
//        accountRepository.createAccount(Account.builder()
//                .cardApply(createAccountRequestDto.getCardApply())
//                .accountType(createAccountRequestDto.getAccountType())
//                .customerName(createAccountRequestDto.getCustomerName())
//                .phoneNumber(createAccountRequestDto.getPhoneNumber())
//                .email(createAccountRequestDto.getEmail())
//                .socialNumber(createAccountRequestDto.getSocialNumber())
//                .accountId(createAccountRequestDto.getAccountType().toString()+Instant.now()) // (?)
//                .userId(7L/*하드코딩*/)
//                .createDate(Date.from(Instant.now()))
//                .balance(0L)
//                .build()
//        );
    }

    public void remittance(RemittanceDto remittanceDto){
        accountRepository.updateBalance(remittanceDto.getWithdraw(), -remittanceDto.getAmount());
        accountRepository.updateBalance(remittanceDto.getDeposit(), remittanceDto.getAmount());
        accountRecordRepository.insertAccountRecord(
                AccountRecord.builder()
                        .description(remittanceDto.getDescription())
                        .amount(remittanceDto.getAmount())
                        .depositId(remittanceDto.getDeposit())
                        .withdrawId(remittanceDto.getWithdraw())
                        .transferDate(LocalDateTime.now())
                        .build());
    }
}
