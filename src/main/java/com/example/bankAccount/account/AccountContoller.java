package com.example.bankAccount.account;

//import bootcamp.accenture.demo.student.Student;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.example.bankAccount.account.AccountMapper.toAccount;
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
    public ResponseEntity<Account> createAccount(@RequestBody AccountDto accountDto) {
        return ok(accountService.createAccount(toAccount(accountDto)));
    }

    @PostMapping("/get")
    public Account getAccountById(@RequestParam Long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
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
    @DeleteMapping
    public void deleteAccount(@RequestParam Long accountId){
        accountService.deleteAccount(accountId);
    }
}

