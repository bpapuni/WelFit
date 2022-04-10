package com.example.welfit;

import java.time.LocalDateTime;

public class Reservation extends User {
    private String className;
    private LocalDateTime time;

    public Reservation(String email, String firstName, String lastName, String className, LocalDateTime time) {
        super(email, firstName, lastName);
        this.className = className;
        this.time = time;
    }

    public String getClassName() {
        return className;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
