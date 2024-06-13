package com.magicvet.infrastructure.database;

import com.magicvet.domain.model.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBService {
    private final DatabaseConfig databaseConfig;

    public DBService(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public Connection getConnection() {
        return databaseConfig.getConnection();
    }

    public boolean checkUserExist(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Login successfully!");
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking user " + e.getMessage());
        }

        System.out.println("Something went wrong. Check login & password or register.");
        return false;
    }

    public int addUserToDB(String email, String password, String phone, String name) {
        String query = "INSERT INTO users (email, password, phone, name, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP) RETURNING id";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, name);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println("Error registering user " + e.getMessage());
        }
        return 0;
    }

    public boolean addPetToDB(Pet pet) {
        String query = "INSERT INTO pets (user_id, name, type, age, breed, gender, created_at) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, pet.getOwnerId());
            preparedStatement.setString(2, pet.getName());
            preparedStatement.setString(3, pet.getType());
            preparedStatement.setInt(4, pet.getAge());
            preparedStatement.setString(5, pet.getBreed());
            preparedStatement.setString(6, pet.getGender());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error registering pet: " + e.getMessage());
        }

        return false;
    }

    public int getOwnerIdByEmail(String email) {
        String query = "SELECT id FROM users WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching owner ID: " + e.getMessage());
        }
        return -1;
    }
}
