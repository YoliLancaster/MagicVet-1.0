package com.magicvet.auth;

import com.magicvet.service.ClientService;

import java.util.Scanner;

public class EntityRegister {

    public static void register(String email, String password){

        System.out.println("Registration. Please check your data.\n Your email is: " + email + "\n Your password is: " + password);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Is it correct?\n1 for SAVE\n2 for CHANGE\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch(choice) {
            case 1:
                ClientService clientService = new ClientService();
                clientService.createOwner(email, password);
                break;
            case 2:

                break;
            default:
                System.out.println("Irrelevant operation.");
        }


    }
}
