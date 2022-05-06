package com.revature.banking.daos;

import com.revature.banking.models.account;
import com.revature.banking.models.history;
import com.revature.banking.models.user;
import com.revature.banking.util.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.revature.banking.MainDriver.loggedinAccount;
import static com.revature.banking.MainDriver.loggedinEmail;

public class HistoryDao implements Crudable<history>{

    @Override
    public history create(history newObject) {
        return null;
    }

    @Override
    public history[] findAll() throws IOException {

        history[] userHistory = new history[50];
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from history where account_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.valueOf(loggedinAccount)); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords


            while (rs.next()) { // the last line of the file is null
                history history1 = new history();

                history1.setId(rs.getInt("id")); // this column lable MUST MATCH THE DB
                history1.setAbility(rs.getString("action"));
                history1.setAccountID(rs.getInt("account_ID"));
                history1.setValue(rs.getInt("value"));

                userHistory[index] = history1;
                index++;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userHistory;
    }

    @Override
    public history findById(String id) {
        return null;
    }

    @Override
    public boolean update(history updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }
}
