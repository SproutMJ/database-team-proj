package com.example.databaseproject.service;

import com.example.databaseproject.domain.account.Account;
import com.example.databaseproject.domain.creditcard.CreditCard;
import com.example.databaseproject.domain.user.User;
import com.example.databaseproject.dto.account.request.RemittanceDto;
import com.example.databaseproject.dto.account.response.AccountInfoDTO;
import com.example.databaseproject.dto.creditcard.request.PayCardDTO;
import com.example.databaseproject.dto.creditcard.response.CreditCardInfoDTO;
import com.example.databaseproject.dto.param.During;
import com.example.databaseproject.repository.jdbc.AccountRepository;
import com.example.databaseproject.repository.jdbc.CreditCardRepository;
import com.example.databaseproject.repository.jdbc.TransactionRepository;
import com.example.databaseproject.repository.jdbc.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class QueryService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final CreditCardRepository creditCardRepository;
    private final TransactionRepository transactionRepository;
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

    @Transactional
    public void payOfCard(PayCardDTO payCardDTO) throws Exception {
        CreditCard card = creditCardRepository.findByCardNumber(payCardDTO.getCardNumber());
        if(payCardDTO.getAmount()+ card.getPayAmount()>card.getPayLimit())
            throw new Exception("카드 한도 초과");
        transactionRepository.payCardAndAccount(payCardDTO.getAmount(), card.getId(), card.getAccount(), payCardDTO.getDesc());
    }

    @Transactional
    public void transfer(RemittanceDto remittanceDto) throws Exception {
        Account withdraw = accountRepository.findByAccountNumber(remittanceDto.getWithdraw());
        Account deposit = accountRepository.findByAccountNumber(remittanceDto.getDeposit());
        if(withdraw.getBalance()- remittanceDto.getAmount() < 0)
            throw new Exception("이체 금액 부족");
        transactionRepository.accountTransfer(withdraw.getId(), deposit.getId(), remittanceDto.getAmount(), remittanceDto.getDescription());
    }
}
