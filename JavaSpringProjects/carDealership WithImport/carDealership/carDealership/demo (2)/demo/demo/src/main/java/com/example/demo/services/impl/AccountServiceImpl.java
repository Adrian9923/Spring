package com.example.demo.services.impl;

import com.example.demo.models.Account;
import com.example.demo.repositories.AccountRepository;
import com.example.demo.services.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdraw(BigDecimal amount, Long id) {
        Account account = accountRepository.findAccountById(id);
        if (account != null && account.getBalance().compareTo(amount) >= 0) {
            account.setBalance(account.getBalance().subtract(amount));
        }
    }

    @Override
    public void transfer(BigDecimal amount, Long id) {
        Account account = accountRepository.findAccountById(id);
        if (account != null) {
            account.setBalance(account.getBalance().add(amount));
        }
    }

}
