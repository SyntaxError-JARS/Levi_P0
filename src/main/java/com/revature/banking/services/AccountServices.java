package com.revature.banking.services;

import com.revature.banking.daos.AccountsDao;
import com.revature.banking.exceptions.ResourcePersistanceException;
import com.revature.banking.models.account;
import com.revature.banking.models.user;
import com.revature.banking.util.logging.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccountServices {
    private AccountsDao accountsDao = new AccountsDao();

    public AccountServices(AccountsDao accountsDao) {
    }

    public ArrayList<account> readAccounts(String email) {
        ArrayList<account> accounts = new ArrayList<>();
        try {
            accounts = accountsDao.findAll(email);
            for (int i = 0; i < accounts.size(); i++) {
                account account = accounts.get(i);
                System.out.println(account.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
        return accounts;
    }

    public void deposit(String value, String id) {
        accountsDao.deposit(value, id);
    }

    public void withdraw(String value, String id) {

        accountsDao.withdraw(value, id);
    }

    public boolean registerAccount(account newAccount) {
        if (!validateAccountInput(newAccount)) { // checking if false
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateAccountInput(newAccount);

        account persistedAccount = accountsDao.create(newAccount);

        if (persistedAccount == null) {
            throw new RuntimeException();
        }
        System.out.println("Account has been registered: " + newAccount);
        return true;
    }

    private boolean validateAccountInput(account newAccount) {
        if (newAccount == null) return false;
        if (newAccount.getAccountID() == 0) return false;
        if (newAccount.getAccountName() == null || newAccount.getAccountName().trim().equals("")) return false;
        return newAccount.getEmail() != null || !newAccount.getEmail().trim().equals("");
    }

    public account readAccountById(String id) {
        Logger logger = null;
        account account = new account();
        try {
            account = accountsDao.findById(id);
        } catch (ResourcePersistanceException e) {
            logger.warn("Id was not found");
        }
        return account;
    }

    public boolean deleteAccount(String id) throws SQLException {
        Logger logger = null;
        try {
            accountsDao.findById(id);
            return accountsDao.delete(id);
        } catch (Exception e) {
            logger.warn("Id was not found");
            return false;
        }
    }
}
