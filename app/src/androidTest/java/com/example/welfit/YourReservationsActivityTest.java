package com.example.welfit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.not;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class YourReservationsActivityTest {
    @Rule
    public ActivityScenarioRule<YourReservationsActivity> activityTestRule = new ActivityScenarioRule<>(YourReservationsActivity.class);
    private ActivityScenario reservationsActivity;

    @Before
    public void setUp() throws Exception {
        reservationsActivity = activityTestRule.getScenario();
    }

    @After
    public void tearDown() throws Exception {
        reservationsActivity = null;
    }

    @Test
    public void BottomAppBarShows() {
        onView(withId(R.id.action_edit)).perform(click());
        onView(withId(R.id.bottom_bar)).check(matches(isDisplayed()));
    }

    @Test
    public void BottomAppBarCloses() throws InterruptedException {
        Thread.sleep(200);
        onView(withId(R.id.action_edit)).perform(click());
        onView(withId(R.id.bottom_bar)).check(matches(isDisplayed()));
        onView(withId(R.id.action_delete)).perform(click());
        onView(withId(R.id.bottom_bar)).check(matches(not(isDisplayed())));
    }
}