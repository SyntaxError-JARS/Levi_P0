package com.revature.banking.util;

// Design Patterns

/*
    Singleton Design Pattern
    - Creational Pattern
    - Restricts that only a signle instance of this class can be made within the application
    - Constructor cannot be invoked outside of this class
    - Eager or Lazy singleton

    Factory Design Pattern
    - Creational PAttern
    - used to abstract away the creation/instantiation of the class

 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory(); // instead Eager Singleton

    private ConnectionFactory(){

    }

    static {
        // Reflections are just viewing a class
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    public Connection getConnection() {

        Connection conn = null;

        // String url = "jdbc:postgresql://localhost:5432/postgres"; // default url will connect you to public
        // TODO: WE NEED TO FIX THIS
        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=banking"; // default url will connect you to public
        String user = "postgres";
        String password = "password";

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
