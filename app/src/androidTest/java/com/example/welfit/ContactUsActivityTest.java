package com.example.welfit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ContactUsActivityTest {
    @Rule
    public ActivityScenarioRule<ContactUsActivity> activityScenarioRule = new ActivityScenarioRule<>(ContactUsActivity.class);
    private ActivityScenario contactUsActivity;
    private String errorMsg;

    @Before
    public void setUp() throws Exception {
        contactUsActivity = activityScenarioRule.getScenario();
        errorMsg = "Message cannot be left empty.";
    }

    @After
    public void tearDown() throws Exception {
        contactUsActivity = null;
    }

    @Test
    public void ErrorMessageDisplays() {
        onView(withId(R.id.message)).perform(clearText());
        onView(withId(R.id.send_btn)).perform(click());
        onView(withId(R.id.contact_error)).check(matches(withText(errorMsg)));
    }
}