package com.revature.banking.services;

import com.revature.banking.daos.HistoryDao;
import com.revature.banking.models.history;

import java.io.IOException;
import java.util.ArrayList;


public class HistoryServices {

    private HistoryDao historyDao = new HistoryDao();

    public HistoryServices(HistoryDao historyDao) {
    }

    public ArrayList<history> readHistory(String ID) {

        ArrayList<history> histories = new ArrayList<>();
        try {
            histories = historyDao.findAll(ID);
            for (int i = 0; i < histories.size(); i++) {
                history history = histories.get(i);
                System.out.println(history.toString());
            }
        } catch (IOException | NullPointerException e) {
            // e.printStackTrace();
        }
        return histories;
    }


}
