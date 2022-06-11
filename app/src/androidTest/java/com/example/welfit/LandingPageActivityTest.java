package com.example.welfit;

import static org.junit.Assert.*;

import android.widget.Button;
import android.widget.ImageButton;

import androidx.test.core.app.ActivityScenario;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
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

public class LandingPageActivityTest {
    @Rule
    public ActivityScenarioRule<LandingPageActivity> activityScenarioRule = new ActivityScenarioRule<>(LandingPageActivity.class);
    private ActivityScenario landingPageActivity;

    @Before
    public void setUp() throws Exception {
        landingPageActivity = activityScenarioRule.getScenario();
    }

    @After
    public void tearDown() throws Exception {
        landingPageActivity = null;
    }

    @Test
    public void ButtonsWork() {
        onView(withId(R.id.btn_reservations)).perform(click());
        onView(withId(R.id.reservationsActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.btn_exercises)).perform(click());
        onView(withId(R.id.exercisesActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.btn_membership)).perform(click());
        onView(withId(R.id.membershipActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.btn_contactus)).perform(click());
        onView(withId(R.id.contactUsActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.btn_view_users)).perform(click());
        onView(withId(R.id.viewUsersActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.qr_scanner)).perform(click());
        onView(withId(R.id.qrScannerActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.stopwatch)).perform(click());
        onView(withId(R.id.stopwatchActivity)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.btn_logout)).perform(click());
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()));
    }
}