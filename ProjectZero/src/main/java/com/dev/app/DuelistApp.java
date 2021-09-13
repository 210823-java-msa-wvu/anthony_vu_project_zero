package com.dev.app;

import com.dev.models.User;
import com.dev.repositories.UserRepo;
import com.dev.services.MasterDeck;
import com.dev.services.UserDeck;
import com.dev.services.UserServices;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DuelistApp {

    public static Scanner scanner = new Scanner(System.in);
    public static UserDeck userDeck = new UserDeck();
    public static UserServices userServices = new UserServices();
    public static MasterDeck deckMaster = new MasterDeck();

    public static void main(String[] args) {

        boolean running = true;

        while (running) {

            System.out.println("Welcome Duelist! Select your option below:");
            System.out.println("(1) Login with Existing User\n(2) Duelist Signup\n(0) Quit");

            int input = scanner.nextInt();

            switch (input) {
                case 1: {
                    scanner.nextLine();

                    System.out.println("Enter your Username: ");
                    String username = scanner.nextLine();

                    System.out.println("Enter in your Password: ");
                    String password = scanner.nextLine();

                    User logInResults = userServices.login(username, password);

                    if (logInResults != null) {
                        if (logInResults.getAdmin()) {
                        System.out.println("Welcome Master Kaiba!");
                            deckMaster.deckMaster(username);

                        } else {
                            System.out.println("\nYou have successfully entered the Dueling Ring!\n");
                            userDeck.deck(username);
                        }
                        running = false;
                    } else {
                        System.out.println("\nYour credentials do not match.\n");
                    }
                    break;
                }
                case 2: {

                    scanner.nextLine();

                    System.out.println("Enter your Username: ");
                    String username = scanner.nextLine();

                    System.out.println("\nEnter in your Password: ");
                    String password = scanner.nextLine();

                    System.out.println("]nAre you an Admin: (True/False)");
                    boolean admin = scanner.nextBoolean();

                    boolean result = userServices.newDuelist(username);

                    if (result) {
                        User u = new User();
                        u.setUsername(username);
                        u.setPassword(password);
                        u.setAdmin(admin);

                        userServices.createUser(u);
                    }
                }
                break;

                case 0: {
                    System.out.println("We will meet again!");
                    running = false;
                    break;
                }

                default:
                    System.out.println("You have entered an invalid input. Please try again.");
                }

        }
        scanner.close();

    }

}
