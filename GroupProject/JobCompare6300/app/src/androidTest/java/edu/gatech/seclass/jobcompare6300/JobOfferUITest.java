package edu.gatech.seclass.jobcompare6300;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.hasErrorText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.action.ViewActions.replaceText;

import org.junit.Rule;
import org.junit.Test;

public class JobOfferUITest {
    @Rule
    public ActivityScenarioRule MainActivityRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void saveClick() throws Exception{

        onView(withId(R.id.JobOfferButtonID)).perform(click());



        onView(withId(R.id.textJobOfferTitle)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferCompany)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferLocation)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferCOL)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferSalary)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferBonus)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferRSU)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferRelocation)).perform(replaceText(""));
        onView(withId(R.id.currentJobOfferPTO)).perform(replaceText(""));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SaveJobOfferButtonID)).perform(scrollTo(),click());

        onView(withId(R.id.textJobOfferTitle)).check(matches(hasErrorText("Value cannot be empty")));
        onView(withId(R.id.currentJobOfferCompany)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobOfferLocation)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobOfferCOL)).check(matches(hasErrorText("Value cannot be empty")));
        onView(withId(R.id.currentJobOfferSalary)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobOfferBonus)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobOfferRSU)).check(matches(hasErrorText("Value cannot be empty")));
        onView(withId(R.id.currentJobOfferRelocation)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobOfferPTO)).check(matches(hasErrorText("Input cannot be empty")));
    }

}

