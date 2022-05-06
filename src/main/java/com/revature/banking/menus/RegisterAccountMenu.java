package com.revature.banking.menus;

import com.revature.banking.models.account;
import com.revature.banking.models.user;
import com.revature.banking.services.AccountServices;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.revature.banking.MainDriver.loggedinEmail;
import static com.revature.banking.MainDriver.menulocation;

public class RegisterAccountMenu extends Menu{

    private AccountServices accountServices = new AccountServices();

    public RegisterAccountMenu(BufferedReader terminalReader) {
        super("RegisterAccount", "/registerAccount", terminalReader);
    }

    @Override
    public void render() throws Exception {
        //TODO add a int only
        System.out.println("Enter Account ID?");
        int accountID = Integer.parseInt(terminalReader.readLine());

        System.out.println("What is your account name?");
        String accountName = terminalReader.readLine();

        account newAccount = new account(loggedinEmail, accountName, accountID);
        System.out.println("Here is the trainer that was provided by the user: " + newAccount);
        accountServices.registerAccount(newAccount);
        menulocation=3;
    }
}
