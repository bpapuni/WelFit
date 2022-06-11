package com.example.welfit;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
    private int id, userId;
    private String className, classDate, classTime;

    public Reservation(int id, int userId, String className, String classDate, String classTime) {
        this.id = id;
        this.userId = userId;
        this.className = className;
        this.classDate = classDate;
        this.classTime = classTime;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getClassName() {
        return className;
    }
    public String getClassDate() { return classDate; }
    public String getClassTime() { return classTime; }
}
