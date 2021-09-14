package com.dev.models;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class User {

    private Integer id;
    private String username;
    private String password;
    private Boolean admin;

    public User() {
    }

    public User(String username, String password, Boolean admin) {
        this.username = username;
        this.password = password;
        this.admin = admin;
    }

    public User(Integer id, String username, String password, Boolean admin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.admin = admin;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return  "ID: " + id +
                " | Username: " + username +
                " | Password: " + password +
                " | Admin: " + admin;
    }
}
