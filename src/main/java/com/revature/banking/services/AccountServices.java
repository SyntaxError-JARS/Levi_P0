package com.revature.banking.services;

import com.revature.banking.daos.AccountsDao;
import com.revature.banking.daos.UserDao;
import com.revature.banking.models.account;
import com.revature.banking.models.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static com.revature.banking.MainDriver.loggedinEmail;

public class AccountServices {
    private AccountsDao accountsDao = new AccountsDao();

    public void readAccounts() {
        System.out.println("Begin reading Accounts in our file database.");
        account[] accounts = new account[0];
        try {
            accounts = accountsDao.findAll();
            System.out.println("All accounts have been found here are the results: \n");
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
        System.out.println("User trying to be registered: " + newAccount);
        if(!validateAccountInput(newAccount)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateAccountInput(newAccount);

        account persistedAccount = accountsDao.create(newAccount);

        if(persistedAccount == null){
            throw new RuntimeException();
        }
        System.out.println("Trainer has been persisted: " + newAccount);
        return true;
    }
    private boolean validateAccountInput(account newAccount) {
        System.out.println("Validating Trainer: " + newAccount);
        if(newAccount == null) return false;
        if(newAccount.getAccountID() == 0) return false;
        if(newAccount.getAccountName() == null || newAccount.getAccountName().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");
    }
}
