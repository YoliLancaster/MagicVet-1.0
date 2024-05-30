package com.magicvet.service;

import com.magicvet.auth.Authenticator;
import com.magicvet.auth.EntityRegister;
import com.magicvet.auth.JDBCInsert;

import java.sql.*;
import java.util.Scanner;

public class ClientService {
    Scanner scanner = new Scanner(System.in);

    public void createOwner(String email, String password){
        System.out.println("We need some additional data: ");
        System.out.println("What is your Name?");
        String name = scanner.nextLine();
        System.out.println("Please, provide your phone: ");
        String phone = scanner.nextLine();

        //String insertUserSQL = "INSERT INTO users (email, password, phone, name) VALUES ('" + email + "', '" + password + "', '" + phone + "', '" + name + "')";

        String insertUserSQL = "INSERT INTO users (email, password, phone, name, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP) RETURNING id";

        try (Connection connection = DriverManager.getConnection(JDBCInsert.DB_URL, JDBCInsert.USER, JDBCInsert.PSW);
             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, name);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");
                System.out.println("Your account has been successfully created. Now add your pet: ");
                System.out.println("What is your pet type?");
                String type = scanner.nextLine();
                System.out.println("What is your Pet Name?");
                String petName = scanner.nextLine();
                System.out.println("How old is " + petName + "?");
                String age = scanner.nextLine();
                System.out.println("What gender is " + petName + "?");
                String gender = scanner.nextLine();
                System.out.println("What breed is " + petName + "?");
                String breed = scanner.nextLine();

                String insertPetSQL = "INSERT INTO pet (user_id, name, type, age, breed, gender, created_at) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

                try (PreparedStatement petPreparedStatement = connection.prepareStatement(insertPetSQL)) {

                    petPreparedStatement.setInt(1, userId);
                    petPreparedStatement.setString(2, petName);
                    petPreparedStatement.setString(3, type);
                    petPreparedStatement.setInt(4, Integer.parseInt(age));
                    petPreparedStatement.setString(5, breed);
                    petPreparedStatement.setString(6, gender);

                    int petRowsAffected = petPreparedStatement.executeUpdate();

                    if (petRowsAffected > 0) {
                        System.out.println("Pet successfully added.");
                    } else {
                        System.out.println("Error while adding pet. Try again.");
                    }

                } catch (SQLException e) {
                    System.err.println("Error while adding pet data: " + e.getMessage());
                }
            } else {
                System.out.println("Error while creating user. Try again.");
            }

        } catch (SQLException e) {
            System.err.println("Error while setting user data: " + e.getMessage());
        }
    }
}
