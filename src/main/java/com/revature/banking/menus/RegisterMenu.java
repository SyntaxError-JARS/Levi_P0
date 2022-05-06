package com.revature.banking.menus;

import com.revature.banking.models.user;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterMenu extends Menu{

    private UserServices userServices = new UserServices();

    public RegisterMenu(BufferedReader terminalReader) {
        super("RegisterUser", "/registerUser", terminalReader);
    }

    @Override
    public void render() throws Exception {

        System.out.println("What is your full name?");
        String fullName = terminalReader.readLine();

        System.out.println("What is your email?");
        String email = terminalReader.readLine();

        System.out.println("What is your password?");
        String password = terminalReader.readLine();

        System.out.println("Re-enter password");
        String passwordCheck = terminalReader.readLine();

        // What's happening here???
        // Breaking or splitting the String fullName into an String array by " " spaces
        String[] nameArray = fullName.split(" ");
        String fname = nameArray[0];
        String lname = nameArray[1];

        // What's happening here??
        //
        if (!password.equals(passwordCheck)) { // password != passwordCheck
            System.out.println("Passwords don't match");
            return; // why return??? Control Flow, this breaks this method and returns us to main
        }


        user newUser = new user(fname, lname, email, password);
        userServices.registerUser(newUser);
    }
}
