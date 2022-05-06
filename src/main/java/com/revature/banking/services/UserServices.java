package com.revature.banking.services;

import com.revature.banking.daos.UserDao;
import com.revature.banking.models.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UserServices {

    private UserDao userDao = new UserDao();

    public void readUsers(){
        System.out.println("Begin reading Users in our file database.");
        user[] users = new user[0];
        try {
            users = userDao.findAll();
            System.out.println("All users have been found here are the results: \n");
            for (int i = 0; i < users.length; i++) {
                user user = users[i];
                System.out.println(user.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
    }

    // TODO: Implement me to check that the email is not already in our database.
    public boolean validateEmail(String email){
        return userDao.checkEmail(email);
    }
    public boolean validatePassword(String email, String password){
        return userDao.checkPassword(email, password);
    }
    public boolean validateEmailNotUsed(String email){
        userDao.checkEmail(email);
        return false;
    }
    public void getAccounts(String email) {

    }

    public boolean registerTrainer(user newUser){
        System.out.println("User trying to be registered: " + newUser);
        if(!validateUserInput(newUser)){ // checking if false
            System.out.println("User was not validated");
            throw new RuntimeException();
        }

        // TODO: Will implement with JDBC (connecting to our database)
        validateEmailNotUsed(newUser.getEmail());

        user persistedTrainer = userDao.create(newUser);

        if(persistedTrainer == null){
            throw new RuntimeException();
        }
        System.out.println("Trainer has been persisted: " + newUser);
        return true;
    }

    private boolean validateUserInput(user newUser) {
        System.out.println("Validating Trainer: " + newUser);
        if(newUser == null) return false;
        if(newUser.getFirstName() == null || newUser.getFirstName().trim().equals("")) return false;
        if(newUser.getLastName() == null || newUser.getLastName().trim().equals("")) return false;
        if(newUser.getEmail() == null || newUser.getEmail().trim().equals("")) return false;
        return newUser.getPassword() != null || !newUser.getPassword().trim().equals("");
    }
}
