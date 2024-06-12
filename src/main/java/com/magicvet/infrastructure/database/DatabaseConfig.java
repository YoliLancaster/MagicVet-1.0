package com.magicvet.infrastructure.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConfig {
    public static final String DB_URL = "jdbc:postgresql://localhost:5433/magicvet-1.0";
    public static final String USER = "postgres";
    public static final String PSW = "12345";

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PSW);
        } catch (SQLException e) {
            System.err.println("Error while connecting to DB: " + e.getMessage());
        }
        return connection;
    }

    public void createTables() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String createUserTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "phone VARCHAR(20), " +
                    "name VARCHAR(255), " +
                    "visit VARCHAR(255)," +
                    "role VARCHAR(50), " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            ;
            statement.executeUpdate(createUserTable);

            String createPetTable = "CREATE TABLE IF NOT EXISTS pet (" +
                    "id SERIAL PRIMARY KEY, " +
                    "user_id INTEGER NOT NULL, " +
                    "name VARCHAR(255), " +
                    "type VARCHAR(255), " +
                    "age INTEGER, " +
                    "breed VARCHAR(255), " +
                    "gender VARCHAR(255), " +
                    "visit VARCHAR(255), " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)";
            statement.executeUpdate(createPetTable);

            String createDoctorTable = "CREATE TABLE IF NOT EXISTS doctor (" +
                    "id SERIAL PRIMARY KEY, " +
                    "username VARCHAR(255), " +
                    "name VARCHAR(255), " +
                    "surname VARCHAR(255), " +
                    "email VARCHAR(255) UNIQUE NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "salary INTEGER, " +
                    "role VARCHAR(50), " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement.executeUpdate(createDoctorTable);

            String createVisitHisTable = "CREATE TABLE IF NOT EXISTS visitHis (" +
                    "id SERIAL PRIMARY KEY, " +
                    "date TIMESTAMP, " +
                    "sum INTEGER, " +
                    "procedure VARCHAR(255), " +
                    "vet_doc INTEGER, " +
                    "resume TEXT, " +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (vet_doc) REFERENCES doctor(id) ON DELETE CASCADE)";
            statement.executeUpdate(createVisitHisTable);

            statement.executeUpdate("ALTER TABLE visitHis ADD FOREIGN KEY (id) REFERENCES users(visit) ON DELETE CASCADE");
            statement.executeUpdate("ALTER TABLE visitHis ADD FOREIGN KEY (id) REFERENCES pet(visit) ON DELETE CASCADE");

            System.out.println("The tables were successfully created or already exist.");

        } catch (SQLException e) {
            System.err.println("Error creating tables: " + e.getMessage());
        }
    }
}