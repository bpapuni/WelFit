package com.example.welfit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.testing.FragmentScenario;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ReservationsActivityTest {
    @Rule
    public ActivityScenarioRule<ReservationsActivity> activityTestRule = new ActivityScenarioRule<>(ReservationsActivity.class);
    ActivityScenario reservationsActivity;

    @Before
    public void setUp() throws Exception {
        reservationsActivity = activityTestRule.getScenario();
    }

    @After
    public void tearDown() throws Exception {
        reservationsActivity = null;
    }

    @Test
    public void FragmentShows() {
        onView(withId(R.id.btn_boxing)).perform(click());
        onView(withId(R.id.reservation_overlay)).check(matches(isDisplayed()));
    }

    @Test
    public void FragmentCloses() {
        onView(withId(R.id.btn_boxing)).perform(click());
        onView(withId(R.id.reservation_overlay)).check(matches(isDisplayed()));
        onView(withId(R.id.reservation_overlay)).perform(click());
        onView(withId(R.id.reservationsActivity)).check(matches(isDisplayed()));
    }
}