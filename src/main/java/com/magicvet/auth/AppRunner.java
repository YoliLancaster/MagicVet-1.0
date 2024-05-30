package main.java.com.magicvet.auth;

import com.magicvet.auth.Authenticator;
import com.magicvet.auth.EntityRegister;
import com.magicvet.auth.JDBCInsert;
import com.magicvet.service.ClientService;

import java.util.Scanner;

public class AppRunner {

    private final JDBCInsert jdbcInsert;

    public AppRunner() {
        jdbcInsert = new JDBCInsert();
    }


    public void run() {
        jdbcInsert.getConnection();

        System.out.println("Welcome to MagicVet!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you have an account?\n1 for LOGIN\n2 for SIGN IN\n");
        int choice = scanner.nextInt();
        scanner.nextLine();


        Authenticator authService = new Authenticator();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();


        switch(choice) {
            case 1:
                //  TODO: Authenticator logic
                if (authService.auth(email, password)) {
                    System.out.println("Authentication successful!");
                } else {
                    System.out.println("Authentication failed. Registering user...");
                    EntityRegister register = new EntityRegister();
                    register.register(email, password);
                    return;
                }
                break;
            case 2:
                //  TODO: EntityReg logic
                EntityRegister register = new EntityRegister();
                register.register(email, password);
                break;
            default:
                System.out.println("Irrelevant operation.");
        }




        scanner.close();

//        if (Authenticator.auth()) {
//            register.registerClients();
//        }
    }
}
