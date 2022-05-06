package com.revature.banking.menus;
import com.revature.banking.services.UserServices;

import java.io.BufferedReader;

import static com.revature.banking.util.AppState.shutdown;
import static com.revature.banking.MainDriver.menulocation;

public class WelcomeMenu extends Menu{

    private UserServices userServices;

    public WelcomeMenu(BufferedReader terminalReader, UserServices trainerServices) {
        super("Welcome", "/welcome", terminalReader);
        this.userServices = trainerServices;
    }

    @Override
    public void render() throws Exception {
        String welcome = "Welcome to the Levis Bank!";
        String option1 = "1) Login";
        String option2 = "2) Register";
        String option3 = new String("3) Exit Levis Bank"); // This is the same as ""

        System.out.printf("%s \n %s \n %s \n %s", welcome, option1, option2, option3).println();

        System.out.print("\n Select number from above\n >");
        String userSelection = terminalReader.readLine();

        switch (userSelection) {
            case "1":
                System.out.println("User has selected login...");
                menulocation = 1;
                break;
            case "2":
                System.out.println("User has selected register...");
                // register(); // ctrl + left-click
                menulocation =2;
                break;
            case "3":
                System.out.println("User has selected exit...");
                // shutdown application here
                shutdown();
                break;
            default: // why have a default? catch all if input doesn't match any case above.
                System.out.println("No valid user input provide");
                break;
        }
    }
}


