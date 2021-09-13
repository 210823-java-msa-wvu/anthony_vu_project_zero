package com.dev.services;

import com.dev.models.User;
import com.dev.repositories.UserRepo;
import com.dev.utilis.YourFoolException;

public class UserServices {

    UserRepo userRepo = new UserRepo();
    User user = new User();

    public User login(String username, String password) {

        User u = null; // more of the Sole Responsibility Principle at work
        try {
            u = userRepo.getByUsername(username);

            if (u != null) {

                if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                    return u;
                }
            }
        } catch (YourFoolException e) {
            System.out.println("Your are a Fourth Rate Duelist! GET OUT!");
        }

        return null;
    }

    public boolean newDuelist(String username) {

        User u = null; // more of the Sole Responsibility Principle at work
        try {
            u = userRepo.getByUsername(username);

        } catch (YourFoolException e) {
            return true;
        }
        System.out.println("Your are a Fourth Rate Duelist! GET OUT!");
        return false;
    }

    public void createUser(User u){

        userRepo.add(u);

    }
}

