package com.example.welfit;

import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private int userId;
    private String className;
    private LocalDateTime time;

    public Reservation(int id, int userId, String className, LocalDateTime time) {
        this.id = id;
        this.userId = userId;
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
