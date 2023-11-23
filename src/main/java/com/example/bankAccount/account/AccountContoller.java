package com.example.bankAccount.account;

//import bootcamp.accenture.demo.student.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/accounts")
public class AccountContoller {

    private static final Logger logger = LogManager.getLogger(AccountContoller.class);
    @Autowired
    AccountRepository accountRepository;
    private final AccountService accountService;

    //@Autowired
    public AccountContoller(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestParam String name) {
        try {
            accountService.createAccount(name);
            return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Name field is empty", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long accountId) {
        Account account = accountService.getAccountById(accountId);
        if (account != null) {
            return new ResponseEntity<>(account, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(
            @RequestParam Long sourceAccountId,
            @RequestParam Long destinationAccountId,
            @RequestParam double amount) {

        boolean transferResult = accountService.transferFunds(sourceAccountId, destinationAccountId, amount);
        if (transferResult) {
            return new ResponseEntity<>("Funds transferred successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to transfer funds", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> depositFunds(
            @RequestParam Long accountId,
            @RequestParam double amount) {

        try {
            boolean depositResult = accountService.deposit(amount, accountId);
            if (depositResult) {
                return new ResponseEntity<>("Deposit successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to transfer funds", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<String> withdrawFunds(
            @RequestParam Long accountId,
            @RequestParam double amount) {

        try {
            boolean depositResult = accountService.withdraw(amount, accountId);
            if (depositResult) {
                return new ResponseEntity<>("Withdraw successful", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to transfer funds", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}

