package com.revature.banking.daos;

import com.revature.banking.models.user;
import com.revature.banking.util.ConnectionFactory;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class UserDao implements Crudable<user> {

    @Override
    public user create(user newUser) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "insert into bank_user (email, first_name, last_name, password) values (?, ?, ?, ?)"; // incomplete sql statement

            PreparedStatement ps = conn.prepareStatement(sql);

            // 1-indexed, so first ? starts are 1
            ps.setString(1, newUser.getEmail());
            ps.setString(2, newUser.getFirstName());
            ps.setString(3, newUser.getLastName());
            ps.setString(4, newUser.getPassword());

            int checkInsert = ps.executeUpdate();

            if (checkInsert == 0) {
                throw new RuntimeException();
            }

        } catch (SQLException | RuntimeException e) {
            e.printStackTrace();
            return null;
        }
        return newUser;
    }

    @Override
    public ArrayList<user> findAll() throws IOException {

        ArrayList<user> users = new ArrayList<>();
        int index = 0;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from bank_user";
            Statement s = conn.createStatement();


            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                user user1 = new user();

                user1.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
                user1.setLastName(rs.getString("last_name"));
                user1.setPassword(rs.getString("password"));
                user1.setEmail(rs.getString("email"));

                users.add(index, user1);
                index++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(users);
        return users;
    }

    @Override
    public user findById(String email) {


        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from bank_user where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords
            System.out.println(ps);

            if (rs.next() != false) {

                user user1 = new user();

                user1.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
                user1.setLastName(rs.getString("last_name"));
                user1.setPassword(rs.getString("password"));
                user1.setEmail(rs.getString("email"));
                System.out.println("Account " + user1.getFirstName() + " Found, please enter password.");

                return user1;
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
    public boolean update(user updatedObj) {
        return false;
    }

    public user updateEmail(user updatedUser) {
        return updatedUser;
    }

    public user updateFName(user updatedUser) {
        return updatedUser;
    }

    public user updateLName(user updatedUser) {
        return updatedUser;
    }

    public user updatePassword(user updatedUser) {
        return updatedUser;
    }

    @Override
    public boolean delete(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql1 = "delete from account where account_id = ? ";
            String sql2 = "delete from bank_user where email= ? ";

            PreparedStatement ps = conn.prepareStatement(sql1);
            PreparedStatement ps1 = conn.prepareStatement(sql2);
            ps.setString(1, email); // Wrapper class example
            ps1.setString(1, email); // Wrapper class example
            int rs = ps.executeUpdate(); // remember dql, bc selects are the keywords
            int rs1 = ps1.executeUpdate();


        } catch (SQLException e) {
            try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

                String sql2 = "delete from bank_user where email= ? ";

                PreparedStatement ps1 = conn.prepareStatement(sql2);
                ps1.setString(1, email); // Wrapper class example
                int rs1 = ps1.executeUpdate();


            } catch (SQLException i) {
                throw new RuntimeException(i);
            }
        }
        return true;
    }


    public boolean checkEmail(String email) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from bank_user where email = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email); // Wrapper class example
            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords

            if(rs.next()==true){
                return true;
            }else{
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean checkPassword(String email, String password){

        try (Connection conn = ConnectionFactory.getInstance().getConnection();) {

            String sql = "select * from bank_user where email = ? and password = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery(); // remember dql, bc selects are the keywords
            if(rs.next()==true){
                return true;
            }else{
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public user authenticateUser(String email, String password) {

        try (Connection conn = ConnectionFactory.getInstance().getConnection()){
            String sql = "select * from bank_user where email = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(!rs.next()){
                return null;
            }

            user user = new user();

            user.setFirstName(rs.getString("first_name")); // this column lable MUST MATCH THE DB
            user.setLastName(rs.getString("last_name"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));

            return user;

        } catch (SQLException e){
            e.printStackTrace();
            return null;
        }

    }
}