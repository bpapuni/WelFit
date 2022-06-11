package com.example.welfit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class SignUpActivityTest {
    @Rule
    public ActivityScenarioRule<SignUpActivity> activityScenarioRule = new ActivityScenarioRule<>(SignUpActivity.class);
    private ActivityScenario signUpActivity;
    private String firstName, lastName, email, password;

    @Before
    public void setUp() throws Exception {
        signUpActivity = activityScenarioRule.getScenario();
        firstName = "test";
        lastName = "account";
        email = "test@gmail.com";
        password = "testpw";
    }

    @After
    public void tearDown() throws Exception {
        signUpActivity = null;
    }

    @Test
    public void CorrectSignUp() {
        onView(withId(R.id.sign_up_first_name)).perform(typeText(firstName));
        onView(withId(R.id.sign_up_last_name)).perform(typeText(lastName));
        onView(withId(R.id.sign_up_email)).perform(typeText(email));
        onView(withId(R.id.sign_up_password)).perform(typeText(password));
        onView(withId(R.id.sign_up_confirm_password)).perform(typeText(password));
        onView(withId(R.id.btn_sign_up)).perform(click());
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()));
    }

    @Test
    public void EmptyField() {
        onView(withId(R.id.sign_up_first_name)).perform(typeText(firstName));
        onView(withId(R.id.sign_up_last_name)).perform(typeText(lastName));
        onView(withId(R.id.sign_up_email)).perform(typeText(email));
        onView(withId(R.id.sign_up_password)).perform(typeText(password));
        onView(withId(R.id.btn_sign_up)).perform(click());
        onView(withId(R.id.sign_up_error)).check(matches(withText("Fields cannot be left empty!")));
    }

}