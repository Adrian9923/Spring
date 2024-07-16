package com.example.demo.services;

import com.example.demo.models.Account;

import java.math.BigDecimal;


public interface AccountService {
    void withdraw(BigDecimal amount, Long id);
    void transfer(BigDecimal amount, Long id);
}
