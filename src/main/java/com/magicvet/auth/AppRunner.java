package main.java.com.magicvet.auth;

import com.magicvet.auth.JDBCInsert;
import com.magicvet.service.ClientService;

import java.util.Scanner;

public class AppRunner {

    private final EntityRegister register = new EntityRegister();
    private final JDBCInsert jdbcInsert;

    public AppRunner() {
        jdbcInsert = new JDBCInsert();
    }


    public void run() {
        jdbcInsert.getConnection();

        System.out.println("Welcome to MagicVet!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input \n1 for LOGIN, \n2 for SIGN IN:\n");
        int choice = scanner.nextInt();
        scanner.nextLine();

        ClientService clientService = new ClientService();
        //  TODO: Authenticator logic
        Authenticator.auth()

//        if (Authenticator.auth()) {
//            register.registerClients();
//        }
    }
}
