package com.revature.banking.daos;

import com.revature.banking.models.account;
import com.revature.banking.models.user;
import com.revature.banking.util.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AccountsDao implements Crudable<account>{


    @Override
    public account create(account newAccount) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into account values ( ? , ? , ? ,default);"; // incomplete sql statement

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setInt(1, newAccount.getAccountID());
            ps.setString(2, newAccount.getEmail());
            ps.setString(3, newAccount.getAccountName());

            int checkInsert = ps.executeUpdate();
            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newAccount;
    }

    @Override
    public ArrayList<user> findAll() throws IOException {
        return null;
    }


    public ArrayList<account> findAll(String email) throws IOException {

        ArrayList<account> accounts = new ArrayList<>();
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) { // try with resoruces, because Connection extends the interface Auto-Closeable

            String sql = "select * from account where email = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords


            while (rs.next()) { // the last line of the file is null
                account account1 = new account();

                account1.setAccountID(rs.getInt("account_ID")); // this column lable MUST MATCH THE DB
                account1.setEmail(rs.getString("email"));
                account1.setAccountName(rs.getString("account_name"));
                account1.setBalance(rs.getInt("balance"));

                accounts.add(index,account1);
                index++;
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
            return null;
        }
        return accounts;
    }

    @Override
    public account findById(String id) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from account where account_ID = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(id)); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords
            System.out.println(ps);

            if (rs.next() != false) {

                account account1 = new account();

                account1.setAccountID(rs.getInt("account_ID")); // this column lable MUST MATCH THE DB
                account1.setEmail(rs.getString("email"));
                account1.setAccountName(rs.getString("account_name"));
                account1.setBalance(rs.getInt("balance"));

                return account1;
            } else {
                System.out.println("User not found");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean update(account updatedObj) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    public void deposit(String amount, String id) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set balance=balance+? where account_ID=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(amount));
            ps.setInt(2, Integer.parseInt(id));
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords

            System.out.println("Deposit of " + amount + " was successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "";
        if (Integer.parseInt(amount) >= 0) {
            sql = "insert into history values (default,?,'Deposit',?)";
        } else {
            sql = "insert into history values (default,?,'Withdraw',?)";
        }

        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //History RECORD
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.setInt(2, Integer.parseInt(amount));
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void withdraw(String amount, String id){
        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "update account set balance=balance-? where account_ID=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(amount));
            ps.setInt(2, Integer.parseInt(id));
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords

            System.out.println("Withdraw of " + amount + " was successful");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            //History RECORD
            String sql = "insert into history values (default,?,'Withdrawal',?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(id));
            ps.setInt(2, Integer.parseInt(amount));
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
