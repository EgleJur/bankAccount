package com.example.bankAccount.validationUnits;

import com.example.bankAccount.account.Account;
import com.example.bankAccount.account.AccountRepository;
import com.example.bankAccount.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountUtils {

    @Autowired
    private final AccountRepository accountRepository;

    public AccountUtils(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Account does not exist", "id",
                        "Account not found", String.valueOf(id)));
    }
}
