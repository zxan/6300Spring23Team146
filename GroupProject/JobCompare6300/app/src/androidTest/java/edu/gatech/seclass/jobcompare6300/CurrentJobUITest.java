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
import static androidx.test.espresso.action.ViewActions.clearText;

import org.junit.Rule;
import org.junit.Test;

public class CurrentJobUITest {
    @Rule
    public ActivityScenarioRule MainActivityRule = new ActivityScenarioRule(MainActivity.class);

    @Test
    public void saveClick() throws Exception{

        onView(withId(R.id.EditJobButtonID)).perform(click());



        onView(withId(R.id.textCurrentJobTitle)).perform(replaceText(""));
        onView(withId(R.id.currentJobCompany)).perform(replaceText(""));
        onView(withId(R.id.currentJobLocation)).perform(replaceText(""));
        onView(withId(R.id.currentJobCOL)).perform(replaceText(""));
        onView(withId(R.id.currentJobSalary)).perform(replaceText(""));
        onView(withId(R.id.currentJobBonus)).perform(replaceText(""));
        onView(withId(R.id.currentJobRSU)).perform(replaceText(""));
        onView(withId(R.id.currentJobRelocation)).perform(replaceText(""));
        onView(withId(R.id.currentJobPTO)).perform(replaceText(""));

        Espresso.closeSoftKeyboard();
        onView(withId(R.id.SaveJobButtonID)).perform(scrollTo(),click());

        onView(withId(R.id.textCurrentJobTitle)).check(matches(hasErrorText("Value cannot be empty")));
//        onView(withId(R.id.currentJobCompany)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobLocation)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobCOL)).check(matches(hasErrorText("Value cannot be empty")));
        onView(withId(R.id.currentJobSalary)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobBonus)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobRSU)).check(matches(hasErrorText("Value cannot be empty")));
        onView(withId(R.id.currentJobRelocation)).check(matches(hasErrorText("Input cannot be empty")));
        onView(withId(R.id.currentJobPTO)).check(matches(hasErrorText("Input cannot be empty")));
    }

}
