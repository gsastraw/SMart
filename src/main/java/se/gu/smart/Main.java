package se.gu.smart;

import se.gu.smart.model.UserAccount;
import se.gu.smart.model.UserAccountAuthentication;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Running Main...");

        UUID id = UUID.randomUUID(); //These are also for testing purposes.
        System.out.print("Signing up!  \nPlease enter your username: ");
        String name = input.nextLine();
        System.out.print("Please enter your real name: ");
        String displayName = input.nextLine();

        UserAccount user = new UserAccount(id, name, displayName);

        System.out.print("Please enter your password: ");
        String password = input.nextLine();
        UserAccountAuthentication passworded = new UserAccountAuthentication(user, password); //This is probably the
        // wrong way to go about this, but I figure it's a push in the right direction.

        System.out.println("Please log in to verify information!"); //This is just for testing purposes.
        System.out.print("Username: ");
        String logInUser = input.nextLine();
        if (logInUser.equals(user.getUsername())){
            System.out.print("Password:");
            String logInPassword = input.nextLine();
            if (logInPassword.equals(passworded.getPassword())){
                System.out.println("Logged in! Welcome aboard " + user.getDisplayName());
            } else {
                System.out.println("Your password was incorrect Please log in again.");
            }
        } else {
            System.out.println("We didn't find that username! Please try again.");
        }
    }
}
