package com.example.welfit;

import java.time.LocalDateTime;

public class Reservation extends User {
    private String className;

    public Reservation(String firstName, String lastName, String className) {
        super(firstName, lastName);
        this.className = className;
    }

}
