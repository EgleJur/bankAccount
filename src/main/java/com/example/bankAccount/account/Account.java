package com.example.bankAccount.account;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNTS")
public class Account {

    static final Logger logger = LogManager.getLogger(Account.class.getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String clientName;
    @Column
    private double balance;


    public Account() {
        balance = 0.0;
        clientName = "";
    }

    public Account(String clientName) {
        this.clientName = clientName;
        this.balance = 500;
    }

    public Account(double balance, String clientName) {
        this.balance = balance;
        this.clientName = clientName;
    }

    public double deposit(double amount) {
        balance += amount;
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        System.out.println("Insufficient funds.");
        return false;
    }

    public void printBalance() {

        System.out.println("Balance: " + balance);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return clientName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
