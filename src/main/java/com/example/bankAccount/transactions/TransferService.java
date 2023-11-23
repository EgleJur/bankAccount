package com.example.bankAccount.transactions;

import com.example.bankAccount.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public class TransferService {
    public static boolean transfer(Account fromAccount, Account toAccount, double amount, JpaRepository repository) {
        if (amount <= fromAccount.getBalance()) {
            fromAccount.setBalance(fromAccount.getBalance() - amount);
            toAccount.setBalance(toAccount.getBalance() + amount);
            repository.save(fromAccount);
            repository.save(toAccount);
            return true;
        }
        System.out.println("Insufficient funds for transfer.");
        return false;
    }
}
