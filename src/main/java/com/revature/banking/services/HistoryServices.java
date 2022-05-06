package com.revature.banking.services;

import com.revature.banking.daos.AccountsDao;
import com.revature.banking.daos.HistoryDao;
import com.revature.banking.models.account;
import com.revature.banking.models.history;

import java.io.IOException;


public class HistoryServices {

    private HistoryDao historyDao = new HistoryDao();

    public void readHistory() {

        history[] histories = new history[0];
        try {
            histories = historyDao.findAll();
            for (int i = 0; i < histories.length; i++) {
                history history = histories[i];
                System.out.println(history.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
    }


}
