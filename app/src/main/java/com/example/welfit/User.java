package com.example.welfit;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class User {
    private int id;
    private String firstName, lastName, email, password, loggedIn;
    private ArrayList<Reservation> reservations;

    public User(int id, String firstName, String lastName, String email, String password, String loggedIn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.loggedIn = loggedIn;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String isLoggedIn() { return loggedIn; }
    public void setIsLoggedIn(String value) { this.loggedIn = value; }
}
