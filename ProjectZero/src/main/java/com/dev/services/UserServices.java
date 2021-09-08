package com.dev.services;

import com.dev.models.User;
import com.dev.repositories.UserRepo;

public class UserServices {

    UserRepo userRepo = new UserRepo();

    public boolean login(String username, String password) {

        User u = userRepo.getByUsername(username); // more of the Sole Responsibility Principle at work

        if (u != null) {

            if (username.equals(u.getUsername()) && password.equals(u.getPassword())) {
                return true;
            }
        }
        return false;
    }

}
