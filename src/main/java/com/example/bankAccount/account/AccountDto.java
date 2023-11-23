package com.example.bankAccount.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String clientName;

    private String email;

    private LocalDate dob;

//    private double balance;
}
