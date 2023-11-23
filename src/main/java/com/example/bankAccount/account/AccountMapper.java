package com.example.bankAccount.account;

public class AccountMapper {

    public static Account toAccount(AccountDto accountDto){
        Account account = new Account();

        account.setClientName(accountDto.getClientName());
        account.setEmail(accountDto.getEmail());
        account.setDob(accountDto.getDob());
//        account.setBalance(accountDto.getBalance());

        return account;
    }

    public static AccountDto toAccountDto(Account account){
        AccountDto accountDto = new AccountDto();

        accountDto.setClientName(account.getClientName());
        accountDto.setEmail(account.getEmail());
        accountDto.setDob(account.getDob());
//        accountDto.setBalance(account.getBalance());

        return accountDto;
    }

}
