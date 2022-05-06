package com.revature.banking.util;

import com.revature.banking.daos.AccountsDao;
import com.revature.banking.menus.*;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.revature.banking.MainDriver.menulocation;

public class AppState {

    private static boolean isRunning;
    private WelcomeMenu welcomeMenu;
    private RegisterMenu registerMenu;
    private AccountsMenu accountsMenu;
    private LoginMenu loginMenu;
    private RegisterAccountMenu registerAccountMenu;
    // once we add register we will need private final MenuRouter router;

    public AppState() {
        // 2. Generating instance of AppState
        isRunning = true;
        BufferedReader terminalReader = new BufferedReader(new InputStreamReader(System.in));
        UserServices userServices = new UserServices();

        // TODO: Why are we doing all of this!?
        this.welcomeMenu = new WelcomeMenu(terminalReader, userServices);
        this.registerMenu = new RegisterMenu(terminalReader);
        this.loginMenu = new LoginMenu(terminalReader);
        this.accountsMenu = new AccountsMenu(terminalReader);
        this.registerAccountMenu = new RegisterAccountMenu(terminalReader);

    }

    public void startup(){
        try {
            while(isRunning) {
                //default value open menu
                if (menulocation==0){welcomeMenu.render();}

                //if login selected
                if(menulocation==1){
                    menulocation=0;
                    loginMenu.render();
                }else if(menulocation==2){//if register selected
                    menulocation=0;
                    registerMenu.render();
                }
                //check if logged in was successful
                if(menulocation==3){
                    accountsMenu.render();
                }
                if(menulocation==4){
                    registerAccountMenu.render();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void shutdown(){
        isRunning = false;
        System.out.println("Application shutting down...");
    }

}
