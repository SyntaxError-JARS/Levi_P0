package com.revature.banking;

import com.revature.banking.util.AppState;
import java.io.*;

public class MainDriver {
    public static String loggedinEmail;
    public static int loggedinAccount;
    public static int menulocation;

    public static void main(String[] args){

        // 1. AppState instantiated
        AppState appState = new AppState();

        System.out.println("Banking Application starting up....");
        appState.startup();


    }
}

