package com.dev.app;

import com.dev.services.UserServices;

import java.util.Scanner;

public class DuelistApp {

    public static Scanner scanner = new Scanner(System.in);

    public static UserServices userServices = new UserServices();

    public static void main(String[] args) {

        System.out.println("Welcome Duelist! Select your option below.");
        System.out.println("1. Login\n2. Quit");

        int input = scanner.nextInt();

        switch  (input) {
            case 1: {
                scanner.nextLine();

                System.out.println("Enter your Username: ");
                String username = scanner.nextLine();

                System.out.println("Enter in your Password: ");
                String password = scanner.nextLine();

                boolean logInResults = userServices.login(username, password);

                if (logInResults) {
                    System.out.println("You have successfully entered the Dueling Ring!");
                } else {
                    System.out.println("Your credentials do not match.");
                }
                break;
            }
            case 2: {
                System.out.println("We will meet again!");
                break;
            }
        }

        scanner.close();

    }

}
