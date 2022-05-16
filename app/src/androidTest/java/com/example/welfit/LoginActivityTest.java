package com.example.welfit;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static com.google.android.material.internal.ContextUtils.getActivity;
import static org.junit.Assert.*;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class LoginActivityTest {
    private String userEmail, password;

    @Before
    public void setUp() throws Exception {
        userEmail = "brent@gmail.com";
        password = "123456";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void CorrectLogin() {
        onView(withId(R.id.login_email)).perform(typeText(userEmail));
        closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
    }

    @Test
    public void IncorrectLoginEmail() {
        onView(withId(R.id.login_email)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.login_error)).check(matches(withText(R.string.invalid_login_email)));
    }//

    @Test
    public void IncorrectLoginPassword() {
        onView(withId(R.id.login_email)).perform(typeText(userEmail));
        closeSoftKeyboard();
        onView(withId(R.id.login_password)).perform(typeText(""));
        closeSoftKeyboard();
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.login_error)).check(matches(withText(R.string.invalid_login_password)));
    }//

    @Test
    public void signup() {
        onView(withId(R.id.btn_sign_up)).perform(click());
    }
}