package com.revature.banking.daos;

import com.revature.banking.models.account;
import com.revature.banking.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;

import static com.revature.banking.MainDriver.*;

public class AccountsDao implements Crudable<account>{


    @Override
    public account create(account newAccount) {
        System.out.println("Here is the newUser as it enters our DAO layer: " + newAccount); // What happens here? Java knows to invoke the toString() method when printing the object to the terminal

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account values ( ? , ? , ? ,default);"; // incomplete sql statement

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setInt(1, newAccount.getAccountID());
            ps.setString(2, newAccount.getEmail());
            ps.setString(3, newAccount.getAccountName());

            int checkInsert = ps.executeUpdate();
            System.out.println("Test Print" + checkInsert);

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newAccount;
    }

    public account[] findAll() throws IOException {

        account[] userAccounts = new account[10];
        int index = 0; // we want to keep track of where we are placing each trainer from the file into the the array

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from account where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, loggedinEmail); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords


            while (rs.next()) { // the last line of the file is null
                account account1 = new account();

                account1.setAccountID(rs.getInt("account_ID")); // this column lable MUST MATCH THE DB
                account1.setEmail(rs.getString("email"));
                account1.setAccountName(rs.getString("account_name"));
                account1.setBalance(rs.getInt("balance"));

                userAccounts[index] = account1;
                index++;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
        System.out.println("Returning user information.");
        return userAccounts;
    }

    @Override
    public account findById(String id) {
        return null;
    }

    @Override
    public boolean update(account updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void deposit(int amount){
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set balance=balance+? where account_ID=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, amount);
            ps.setInt(2, loggedinAccount);
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords

            System.out.println("Deposit of " + amount + " was successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void withdraw(int amount){
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set balance=balance+? where account_ID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, -amount);
            ps.setInt(2, loggedinAccount);
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords

            System.out.println("Deposit of " + amount + " was successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
