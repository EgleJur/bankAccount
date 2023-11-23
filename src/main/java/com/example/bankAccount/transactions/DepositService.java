package com.example.bankAccount.transactions;

import com.example.bankAccount.account.Account;

public class DepositService {

    public static double depositService(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        return account.getBalance();
    }
}