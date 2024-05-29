package main.java.com.magicvet.auth;

public class Authenticator {

    public static boolean auth() {
        boolean accepted = false;

        //TODO: verify if Client exist, verify regEx password email for the 1st registration,

        System.out.println(accepted ? "Login successfully!" : "Something went wrong. Try again.");
        return accepted;
    }
}
