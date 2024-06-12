package com.magicvet.auth;

import com.magicvet.domain.service.AuthServiceImpl;
import com.magicvet.domain.service.AuthService;
import com.magicvet.domain.service.OwnerRepositoryImpl;
import com.magicvet.infrastructure.database.DBService;
import com.magicvet.infrastructure.database.DatabaseConfig;


import java.util.Scanner;

public class AppRunner {

    private final AuthService authService;

    public AppRunner() {
        DatabaseConfig databaseConfig = new DatabaseConfig();
        DBService dbService = new DBService(databaseConfig);
        OwnerRepositoryImpl ownerRepository = new OwnerRepositoryImpl(dbService);
        this.authService = new AuthServiceImpl(ownerRepository);
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
                } else {
                    System.out.println("Authentication failed. Registering user...");

                    authService.registerOwnerAndPet(email, password, "", "");
                }
                break;
            case 2:
                //  TODO: EntityReg logic
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();

                System.out.print("Enter your phone: ");
                String phone = scanner.nextLine();

                authService.registerOwnerAndPet(email, password, name, phone);
                break;
            default:
                System.out.println("Irrelevant operation.");
        }
        scanner.close();
    }
}
