package com.magicvet.auth;

import java.sql.*;

public class Authenticator {

    public static boolean auth(String email, String password) {
        //TODO: verify if Client exist, verify regEx password email for the 1st registration,

        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(JDBCInsert.DB_URL, JDBCInsert.USER, JDBCInsert.PSW);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // user found
                    System.out.println("Login successfully!");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error auth user " + e.getMessage());
        }

        // if not found user in db
        System.out.println("Something went wrong. Check login & password or register.");
        return false;
    }

}
