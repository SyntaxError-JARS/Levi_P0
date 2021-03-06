package com.revature.banking.menus;

import com.revature.banking.services.AccountServices;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;

import static com.revature.banking.MainDriver.loggedinAccount;
import static com.revature.banking.MainDriver.menulocation;

public class AccountsMenu extends Menu {

    private AccountServices accountServices = new AccountServices();

    public AccountsMenu(BufferedReader terminalReader) {
        super("Account", "/Account", terminalReader);
    }

    @Override
    public void render() throws Exception {
        accountServices.readAccounts();
        //check if user has any accounts
        //return accounts or if no accounts
        //deposit into accounts
        //withdraw into accounts
        System.out.println("Type 1 to list/select account || Type 2 to create new account || Type 3 to exit");
        int dec = Integer.parseInt(terminalReader.readLine());
        if (dec == 1) {
            System.out.println("Please choose an account by typing accountID");
            String accountID = terminalReader.readLine();
            System.out.println("Checking if account " + accountID + " is available");
            //TODO: Implement check case
            System.out.println("account " + accountID + " was selected");
            loggedinAccount = Integer.parseInt(accountID);
            System.out.println("Please choose an account by typing accountID");
            System.out.println(" ");
            System.out.println("Type 1 for deposit || Type 2 for withdrawal");
            String dw = terminalReader.readLine();
            if (Integer.parseInt(dw) == 1) {
                System.out.println("What is your deposit amount");
                String amount = terminalReader.readLine();
                accountServices.deposit(Integer.parseInt(amount));
            } else if ((Integer.parseInt(dw) == 2)) {
                System.out.println("What is your withdrawal amount");
                String amount = terminalReader.readLine();
                accountServices.withdraw(Integer.parseInt(amount));
            }
        } else if (dec == 2) {
            menulocation = 4;
        } else if (dec==3) {
            menulocation = 0;
        }
    }
}
