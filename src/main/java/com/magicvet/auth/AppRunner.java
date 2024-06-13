package com.magicvet.auth;

import com.magicvet.domain.model.Pet;
import com.magicvet.domain.service.AuthServiceImpl;
import com.magicvet.domain.service.AuthService;
import com.magicvet.domain.service.OwnerRepositoryImpl;
import com.magicvet.domain.service.PetRepositoryImpl;
import com.magicvet.infrastructure.database.DBService;
import com.magicvet.infrastructure.database.DatabaseConfig;


import java.util.Scanner;

public class AppRunner {

    private final AuthService authService;

    public AppRunner() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        DBService dbService = new DBService(databaseConfig);
        OwnerRepositoryImpl ownerRepository = new OwnerRepositoryImpl(dbService);
        PetRepositoryImpl petRepository = new PetRepositoryImpl(dbService);
        this.authService = new AuthServiceImpl(ownerRepository, petRepository);
    }


    public void run() {
        System.out.println("Welcome to MagicVet!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you have an account?\n1 for LOGIN\n2 for SIGN IN\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        // Authenticator authService = new Authenticator();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();


        switch (choice) {
            case 1:
                //  TODO: Authenticator logic
                if (authService.authenticate(email, password)) {
                    System.out.println("Authentication successful!");
                    afterLoginMenu(scanner, email);
                } else {
                    System.out.println("Authentication failed. Registering user...");

                    registerOwnerAndPetProcess(authService, email, password, scanner);
                }
                break;
            case 2:
                //  TODO: EntityReg logic
                registerOwnerAndPetProcess(authService, email, password, scanner);
                break;
            default:
                System.out.println("Irrelevant operation.");
        }
        scanner.close();
    }

    private void afterLoginMenu(Scanner scanner, String email) {
        System.out.println("Welcome back! What would you like to do?");
        System.out.println("1. Add a pet");
        System.out.println("2. Logout");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                int ownerId = authService.getOwnerIdByEmail(email);
                registerPetProcess(ownerId, scanner);
                break;
            case 2:
                System.out.println("Logging out...");
                break;
            default:
                System.out.println("Invalid operation.");
        }
    }

    private static Pet collectPetData(Scanner scanner, int ownerId) {
        System.out.println("What is your pet type?");
        String petType = scanner.nextLine();

        System.out.println("What is your Pet Name?");
        String petName = scanner.nextLine();

        System.out.println("How old is " + petName + "?");
        int petAge = scanner.nextInt();
        scanner.nextLine();

        System.out.println("What gender is " + petName + "?");
        String petGender = scanner.nextLine();

        System.out.println("What breed is " + petName + "?");
        String petBreed = scanner.nextLine();

        return new Pet(ownerId, petName, petType, petAge, petBreed, petGender);
    }

    private static void registerOwnerAndPetProcess(AuthService authService, String email, String password, Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your phone: ");
        String phone = scanner.nextLine();

        System.out.println("Your account has been successfully created. Now add your pet:");

        int ownerId = authService.registerOwner(email, password, name, phone);
        Pet pet = collectPetData(scanner, ownerId);

        authService.createPetForOwner(ownerId, pet);
    }

    private void registerPetProcess(int ownerId, Scanner scanner) {
        Pet pet = collectPetData(scanner, ownerId);
        authService.createPetForOwner(ownerId, pet);
    }
}
