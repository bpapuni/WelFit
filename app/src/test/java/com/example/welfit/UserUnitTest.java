package com.example.welfit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserUnitTest {
    private User testUser;
    private int id;
    private String firstName, lastName, email, password, loggedIn;

    @Before
    public void setUp() throws Exception {
        id = 0;
        firstName = "Test";
        lastName = "User";
        email = "test@gmail.com";
        password = "test12";
        loggedIn = "false";
    }

    @After
    public void tearDown() throws Exception {
        id = 0;
        firstName = null;
        lastName = null;
        email = null;
        password = null;
        loggedIn = null;
        testUser = null;
    }

    @Test
    public void UserConstructorTest(){
        testUser = new User(id, firstName, lastName, email, password, loggedIn);
        assertEquals(0, testUser.getId());
        assertEquals(firstName, testUser.getFirstName());
        assertEquals(lastName, testUser.getLastName());
        assertEquals(email, testUser.getEmail());
        assertEquals(password, testUser.getPassword());
    }

    @Test
    public void UserMethodsTest(){
        testUser = new User(id, firstName, lastName, email, password, loggedIn);
        testUser.setId(1);
        testUser.setFirstName("Brent");
        testUser.setLastName("Papuni");
        testUser.setEmail("brent@gmail.com");
        testUser.setPassword("testpw");
        assertEquals(1, testUser.getId());
        assertEquals("Brent", testUser.getFirstName());
        assertEquals("Papuni", testUser.getLastName());
        assertEquals("brent@gmail.com", testUser.getEmail());
        assertEquals("testpw", testUser.getPassword());
    }
}