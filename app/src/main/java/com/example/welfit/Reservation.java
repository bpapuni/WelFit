package com.example.welfit;

import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
    private int id;
    private int userId;
    private String className;
    private String time;

    public Reservation(int id, int userId, String className, String time) {
        this.id = id;
        this.userId = userId;
        this.className = className;
        this.time = time;
    }

    public int getUserId() { return userId; }
    public String getClassName() {
        return className;
    }
    public String getClassTime() { return time; }
}
