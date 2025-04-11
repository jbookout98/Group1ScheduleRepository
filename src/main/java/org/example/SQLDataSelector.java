package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataSelector {
    // Connect to your database.
    // Replace server name, username, and password with your credentials
    public static void main(String[] args) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;" +
                        "databaseName=coursesDataBase;integratedSecurity=true;" +
                        "encrypt=true;trustServerCertificate=true";
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            System.out.println("Is Connected To DataBase");
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}