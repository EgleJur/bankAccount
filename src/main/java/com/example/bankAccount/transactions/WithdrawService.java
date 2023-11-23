package com.example.bankAccount.transactions;

import com.example.bankAccount.account.Account;

public class WithdrawService {
    public static boolean withdraw(Account account, double amount) {
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
            return true;
        }
        System.out.println("Insufficient funds.");
        return false;
    }
}
