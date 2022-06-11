package com.example.welfit;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.junit.Assert.*;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ExercisesActivityTest {
    @Rule
    public ActivityScenarioRule<ExercisesActivity> activityScenarioRule = new ActivityScenarioRule<>(ExercisesActivity.class);
    private ActivityScenario exercisesActivity;

    @Before
    public void setUp() throws Exception {
        exercisesActivity = activityScenarioRule.getScenario();
    }

    @After
    public void tearDown() throws Exception {
        exercisesActivity = null;
    }

    @Test
    public void ButtonsDisplayed() {
        findById(R.id.benchBtn).check(matches(isDisplayed()));
        findById(R.id.shoulder_pressBtn).check(matches(isDisplayed()));
        findById(R.id.lat_raisesBtn).check(matches(isDisplayed()));
        findById(R.id.pulldownsBtn).check(matches(isDisplayed()));
        findById(R.id.squatBtn).check(matches(isDisplayed()));
        findById(R.id.deadliftBtn).check(matches(isDisplayed()));
        onView(withId(R.id.dipsBtn)).perform(scrollTo());
        findById(R.id.dipsBtn).check(matches(isDisplayed()));
        findById(R.id.curlsBtn).check(matches(isDisplayed()));
    }

    private static boolean waitForElementUntilDisplayed(ViewInteraction element) {
        int i = 0;
        while (i++ < 10) {
            try {
                element.check(matches(isDisplayed()));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch (Exception e1) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    private static ViewInteraction findById(int itemId) {
        ViewInteraction element = onView(withId(itemId));
        waitForElementUntilDisplayed(element);
        return element;
    }
}