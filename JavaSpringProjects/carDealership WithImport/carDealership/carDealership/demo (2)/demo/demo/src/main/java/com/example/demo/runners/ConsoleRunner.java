package com.example.demo.runners;

import com.example.demo.models.Account;
import com.example.demo.models.User;
import com.example.demo.services.AccountService;
import com.example.demo.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setUsername("Peter");
        user.setAge(20);

        Account account = new Account();
        account.setBalance(new BigDecimal(25000));

        // Associate the User and Account
        account.setUser(user);

        //Sau asa user.setAccounts(new HashSet<>(Collections.singletonList(account)));
        // Create a HashSet to hold the accounts
        Set<Account> accounts = new HashSet<>();
        accounts.add(account);
        user.setAccounts(accounts);


        // Save the user and account
        userService.register(user);



        // Perform the account operations
        this.accountService.withdraw(new BigDecimal(5000), account.getId());
        this.accountService.transfer(new BigDecimal(20000), account.getId());
        this.accountService.withdraw(new BigDecimal(5000), account.getId());
    }
}
