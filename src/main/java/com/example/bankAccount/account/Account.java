package com.example.bankAccount.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ACCOUNTS")
@Data
@AllArgsConstructor
public class Account {

    static final Logger logger = LogManager.getLogger(Account.class.getName());
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String clientName;
    @Column
    private String email;
    @Column
    private LocalDate dob;
    @Column
    private double balance;


    public Account() {
        balance = 0.00;
        clientName = "";
    }

    public Account(String clientName) {
        this.clientName = clientName;
        this.balance = 0.00;
    }

    public Account(double balance, String clientName) {
        this.balance = balance;
        this.clientName = clientName;
    }

}
