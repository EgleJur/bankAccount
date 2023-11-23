package com.example.bankAccount.account;

//import bootcamp.accenture.demo.student.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountContoller {

    private static final Logger logger = LogManager.getLogger(AccountContoller.class);
//    @Autowired
//    AccountRepository accountRepository;
    private final AccountService accountService;

    //@Autowired
    public AccountContoller(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestParam String name) {
        return ok(accountService.createAccount(name));
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Boolean> transferFunds(
            @RequestParam Long sourceAccountId,
            @RequestParam Long destinationAccountId,
            @RequestParam double amount) {
        return ok(accountService.transferFunds(sourceAccountId, destinationAccountId, amount));

    }

    @PostMapping("/deposit")
    public ResponseEntity<Account> depositFunds(
            @RequestParam Long accountId,
            @RequestParam double amount) {

        return ok(accountService.deposit(amount, accountId));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Account> withdrawFunds(
            @RequestParam Long accountId,
            @RequestParam double amount) {
        return ok(accountService.withdraw(amount, accountId));

    }
}

