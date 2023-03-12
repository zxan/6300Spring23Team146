package edu.gatech.seclass.jobcompare6300;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.clearText;

import android.provider.Telephony;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MainPageTest {
    @Rule
    public ActivityScenarioRule MainActivityRule = new ActivityScenarioRule(MainActivity.class);

    @Before
    public void beforeSet() throws Exception{
        Intents.init();
    }
    @After
    public void end() throws Exception{
        Intents.release();
    }

    @Test
    public void click1(){
        onView(withId(R.id.JobOfferButtonID)).perform(click());
        intended(hasComponent(JobOffers.class.getName()));
        onView(withId(R.id.CancelJobOfferButtonID)).perform(scrollTo(),click());
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void click2(){
        onView(withId(R.id.ComparisonSettingsButtonID)).perform(click());
        intended(hasComponent(ComparisonSettings.class.getName()));
    }
    @Test
    public void click3(){
        onView(withId(R.id.EditJobButtonID)).perform(click());
        intended(hasComponent(CurrentJob.class.getName()));
        onView(withId(R.id.CancelJobButtonID)).perform(scrollTo(),click());
        intended(hasComponent(MainActivity.class.getName()));
    }
    @Test
    public void click4(){
        onView(withId(R.id.CompareJobsButtonID)).perform(click());
        intended(hasComponent(CompareJobs.class.getName()));
    }
}
