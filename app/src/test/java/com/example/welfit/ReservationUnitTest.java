package com.example.welfit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservationUnitTest {
    private Reservation testReservation;
    private int id, userId;
    private String className, classDate, classTime;

    @Before
    public void setUp() throws Exception {
        id = 0;
        userId = 0;
        className = "Boxing";
        classDate = "Fri, 10 Jun";
        classTime = "10:00 AM";
    }

    @After
    public void tearDown() throws Exception {
        id = 0;
        userId = 0;
        className = null;
        classDate = null;
        classTime = null;
    }

    @Test
    public void ReservationConstructorTest(){
        testReservation = new Reservation(id, userId, className, classDate, classTime);
        assertEquals(0, testReservation.getId());
        assertEquals(0, testReservation.getUserId());
        assertEquals(className, testReservation.getClassName());
        assertEquals(classDate, testReservation.getClassDate());
        assertEquals(classTime, testReservation.getClassTime());
    }
}