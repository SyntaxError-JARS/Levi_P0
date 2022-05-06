package com.revature.banking.services;

import com.revature.banking.daos.AccountsDao;
import com.revature.banking.models.account;

import java.io.IOException;

import static com.revature.banking.MainDriver.loggedinEmail;

public class AccountServices {
    private AccountsDao accountsDao = new AccountsDao();

    public void readAccounts() {
        account[] accounts = new account[0];
        try {
            accounts = accountsDao.findAll();
            for (int i = 0; i < accounts.length; i++) {
                account account = accounts[i];
                System.out.println(account.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
    }
    public void deposit(int amount){
        accountsDao.deposit(amount);
    }
    public void withdraw(int amount){
        accountsDao.withdraw(amount);
    }

    public boolean registerAccount(account newAccount){
        if(!validateAccountInput(newAccount)){ // checking if false
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateAccountInput(newAccount);

        account persistedAccount = accountsDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("Account has been registered: " + newAccount);
        return true;
    }
    private boolean validateAccountInput(account newAccount) {
        if(newAccount == null) return false;
        if(newAccount.getAccountID() == 0) return false;
        if(newAccount.getAccountName() == null || newAccount.getAccountName().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");
    }
}
