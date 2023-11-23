package com.example.bankAccount.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class AccountService {
    private static final Logger logger = LogManager.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;

    Map<Integer, Account> accounts = new HashMap<>();
    int accountNumberCounter = 1;

    //@Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean createAccount(String name) {
        if (Objects.equals(name, "")) {
            logger.warn("Name was not given");
            throw new IllegalArgumentException("Please enter a name.");
        }
        Account newAccount = new Account(name);
        accountRepository.save(newAccount);
        logger.info("Account created successfully: ");

        return true;
    }

    public Account getAccountById(Long accountId) {

        Account account = accountRepository.findById(accountId).get();
        if (account != null) {
            logger.info("Showing account: " + accountId);
            return account;
        } else {

            logger.warn("Account with ID " + accountId + " not found.");
            throw new NoSuchElementException("Account not found for ID: " + accountId);
        }

    }

    public boolean transferFunds(Long sourceAccountId, Long destinationAccountId, double amount) {

        Account sourceAccount = accountRepository.findById(sourceAccountId).get();
        Account destinationAccount = accountRepository.findById(destinationAccountId).get();
        if (amount <= 0) {
            logger.warn("Amount to transfer was negative");
            throw new IllegalArgumentException("Please enter a positive amount to deposit.");
        }
        if (sourceAccount != null && destinationAccount != null) {
            if (sourceAccount.withdraw(amount)) {
                accountRepository.save(sourceAccount);
                destinationAccount.deposit(amount);
                accountRepository.save(destinationAccount);
                logger.info("Transfer successful form account " + sourceAccountId + " to account " + destinationAccountId);
                return true;
            }
        }
        logger.warn("Transfer was not successful form account " + sourceAccountId + " to account " + destinationAccountId);
        return false;
    }

    public boolean deposit(double amount, Long accountId) throws IllegalArgumentException {
        Account account = accountRepository.findById(accountId).get();
        if (amount <= 0) {
            logger.warn("Amount to deposit was negative");
            throw new IllegalArgumentException("Please enter a positive amount to deposit.");
        }
        account.deposit(amount);
        accountRepository.save(account);
        logger.info("Deposit successful to account " + accountId);
        return true;
    }

    public boolean withdraw(double amount, Long accountId) throws IllegalArgumentException {
        Account account = accountRepository.findById(accountId).get();
        if (amount <= 0) {
            throw new IllegalArgumentException("Please enter a positive amount to deposit.");
        }
        account.withdraw(amount);
        accountRepository.save(account);
        logger.info("Withdraw successful form account " + accountId);
        return true;
    }
}
