package com.example.welfit;

import android.app.Activity;

import androidx.test.core.app.ActivityScenario;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityTestRule = new ActivityScenarioRule<>(LoginActivity.class);
    ActivityScenario loginActivity;
    String userEmail, password;

    @Before
    public void setUp() throws Exception {
        loginActivity = activityTestRule.getScenario();
        userEmail = "admin@gmail.com";
        password = "admin1";
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }

    @Test
    public void CorrectLogin() {
        onView(withId(R.id.login_email)).perform(typeText(userEmail));
        onView(withId(R.id.login_password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.landingPage)).check(matches(isDisplayed()));
    }

    @Test
    public void IncorrectLoginEmail() {
        onView(withId(R.id.login_email)).perform(typeText(""));
        onView(withId(R.id.login_password)).perform(typeText(password));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.login_error)).check(matches(withText(R.string.invalid_login_email)));
    }

    @Test
    public void IncorrectLoginPassword() {
        onView(withId(R.id.login_email)).perform(typeText(userEmail));
        onView(withId(R.id.login_password)).perform(typeText(""));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.login_error)).check(matches(withText(R.string.invalid_login_password)));
    }
}