package com.example.bankAccount.account;

import com.example.bankAccount.transactions.DepositService;
import com.example.bankAccount.transactions.WithdrawService;
import com.example.bankAccount.validationUnits.AccountUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.bankAccount.transactions.DepositService.depositService;
import static com.example.bankAccount.transactions.TransferService.transfer;
import static com.example.bankAccount.validationUnits.ValidationUtilsNotNull.*;
import static com.example.bankAccount.validationUnits.ValidationUtilsPositive.*;

@Service
public class AccountService {
    private static final Logger logger = LogManager.getLogger(AccountService.class);
    @Autowired
    AccountRepository accountRepository;

    private final AccountUtils accountUtils;

    //@Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        accountUtils = new AccountUtils(accountRepository);
    }

    public Account createAccount(String name) {
        isValidByName(name);
        Account newAccount = new Account(name);
        logger.info("Account created successfully: ");

        return accountRepository.save(newAccount);
    }

    public Account getAccountById(Long accountId) {
        return accountUtils.getAccountById(accountId);
    }
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public boolean transferFunds(Long sourceAccountId, Long destinationAccountId, double amount) {
        isValidByAmount(amount);

        Account sourceAccount = accountUtils.getAccountById(sourceAccountId);
        Account destinationAccount = accountUtils.getAccountById(destinationAccountId);
        if (sourceAccount != null && destinationAccount != null) {
            if (transfer(sourceAccount, destinationAccount, amount, accountRepository)) {
                logger.info("Transfer successful form account " + sourceAccountId + " to account " + destinationAccountId);
                return true;
            }
        }
        logger.warn("Transfer was not successful form account " + sourceAccountId + " to account " + destinationAccountId);
        return false;
    }

    public Account deposit(double amount, Long accountId){
        isValidByAmount(amount);
        Account account = accountUtils.getAccountById(accountId);
        depositService(account, amount);
        logger.info("Deposit successful to account " + accountId);
        return accountRepository.save(account);
    }

    public Account withdraw(double amount, Long accountId){
        isValidByAmount(amount);
        Account account = accountUtils.getAccountById(accountId);
        WithdrawService.withdraw(account, amount);
        logger.info("Withdraw successful form account " + accountId);
        return accountRepository.save(account);
    }
}
