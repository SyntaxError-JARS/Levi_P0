package com.revature.banking.menus;

import com.revature.banking.models.user;
import com.revature.banking.services.UserServices;
import com.revature.banking.MainDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.revature.banking.MainDriver.loggedinEmail;
import static com.revature.banking.MainDriver.menulocation;

public class LoginMenu extends Menu{


    private UserServices userServices = new UserServices();

    public LoginMenu(BufferedReader terminalReader) {
        super("Login", "/login", terminalReader);
    }

    @Override
    public void render() throws Exception {

        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        if (userServices.validateEmail(email) == true){
            System.out.println("User Found.... Please enter your Password");
            String password = terminalReader.readLine();
            if(userServices.validatePassword(email,password) == true){
                System.out.println("Successfully logged in");
                loggedinEmail = email;
                menulocation=3;
                //send user to account
            }else {
                System.out.println("Incorrect Password");
            }


        }else{
            System.out.println("User not found :(");
            return;
        }

    }
}
