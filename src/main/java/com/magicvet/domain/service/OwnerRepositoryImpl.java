package com.magicvet.domain.service;

import com.magicvet.domain.model.Owner;
import com.magicvet.infrastructure.database.DBService;
import com.magicvet.infrastructure.persistence.OwnerRepository;

public class OwnerRepositoryImpl implements OwnerRepository {
    //private final ScannerService scannerService;
    //Scanner scanner = new Scanner(System.in);

//    public void createOwner(String email, String password) {
//        System.out.println("We need some additional data: ");
//        System.out.println("What is your Name?");
//        String name = scanner.nextLine();
//        System.out.println("Please, provide your phone: ");
//        String phone = scanner.nextLine();
//
//        //String insertUserSQL = "INSERT INTO users (email, password, phone, name) VALUES ('" + email + "', '" + password + "', '" + phone + "', '" + name + "')";
//
//        String insertUserSQL = "INSERT INTO users (email, password, phone, name, created_at) VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP) RETURNING id";
//
//        try (Connection connection = DriverManager.getConnection(DatabaseConfig.DB_URL, DatabaseConfig.USER, DatabaseConfig.PSW);
//             PreparedStatement preparedStatement = connection.prepareStatement(insertUserSQL)) {
//
//            preparedStatement.setString(1, email);
//            preparedStatement.setString(2, password);
//            preparedStatement.setString(3, phone);
//            preparedStatement.setString(4, name);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            if (rs.next()) {
//                int userId = rs.getInt("id");
//                System.out.println("Your account has been successfully created. Now add your pet: ");
//                System.out.println("What is your pet type?");
//                String type = scanner.nextLine();
//                System.out.println("What is your Pet Name?");
//                String petName = scanner.nextLine();
//                System.out.println("How old is " + petName + "?");
//                String age = scanner.nextLine();
//                System.out.println("What gender is " + petName + "?");
//                String gender = scanner.nextLine();
//                System.out.println("What breed is " + petName + "?");
//                String breed = scanner.nextLine();
//
//                String insertPetSQL = "INSERT INTO pet (user_id, name, type, age, breed, gender, created_at) VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
//
//                try (PreparedStatement petPreparedStatement = connection.prepareStatement(insertPetSQL)) {
//
//                    petPreparedStatement.setInt(1, userId);
//                    petPreparedStatement.setString(2, petName);
//                    petPreparedStatement.setString(3, type);
//                    petPreparedStatement.setInt(4, Integer.parseInt(age));
//                    petPreparedStatement.setString(5, breed);
//                    petPreparedStatement.setString(6, gender);
//
//                    int petRowsAffected = petPreparedStatement.executeUpdate();
//
//                    if (petRowsAffected > 0) {
//                        System.out.println("Pet successfully added.");
//                    } else {
//                        System.out.println("Error while adding pet. Try again.");
//                    }
//
//                } catch (SQLException e) {
//                    System.err.println("Error while adding pet data: " + e.getMessage());
//                }
//            } else {
//                System.out.println("Error while creating user. Try again.");
//            }
//
//        } catch (SQLException e) {
//            System.err.println("Error while setting user data: " + e.getMessage());
//        }
//    }
//}

    private final DBService dbService;

    public OwnerRepositoryImpl(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return dbService.checkUserExist(email, password);
    }

//    @Override
//    public void registerOwner() {
//        String email = scannerService.getInput("Enter your email: ");
//        String password = scannerService.getInput("Enter your password: ");
//        String phone = scannerService.getInput("Enter your phone: ");
//        String name = scannerService.getInput("Enter your name: ");
//
//        Owner owner = new Owner(email, password, name, phone);
//        //boolean ownerRegistered = authRepository.registerOwner(owner);
////        if (ownerRegistered) {
////            System.out.println("User created successfully!");
////        } else {
////            System.out.println("Failed to create user.");
////        }
//        createOwner(owner);
//    }

    @Override
    public int createOwner(Owner owner) {
//        int rowsAffected = dbService.registerUser(email, password, phone,name);
//        if (rowsAffected > 0) {
//            System.out.println("Registration successful!");
//        } else {
//            System.out.println("Registration failed!");
//        }
        return dbService.addUserToDB(owner.getEmail(), owner.getPassword(), owner.getPhone(), owner.getName());
    }

    @Override
    public int getOwnerIdByEmail(String email) {
        return dbService.getOwnerIdByEmail(email);
    }
}