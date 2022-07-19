package com.romanyou.notesapp.espresso;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import static org.hamcrest.CoreMatchers.instanceOf;
import static java.util.EnumSet.allOf;

import android.widget.TextView;
import android.widget.Toolbar;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.romanyou.notesapp.MainActivity;
import com.romanyou.notesapp.AddActivity;
import com.romanyou.*;
import com.romanyou.notesapp.*;
import com.romanyou.notesapp.R;
import com.romanyou.notesapp.controller.NotesController;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testing MainActivity View
 *
 * @Author Roman Behroz
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);




    @Test
    public void addButtonIsDisplayed(){
        onView(withId(R.id.add_button)).check(matches(isDisplayed()));
        onView(withId(R.id.add_button)).check(matches(isClickable()));

    }

    @Test
    public void addButtonOpensAddActivity(){
        onView(withId(R.id.add_button)).perform(click());

        onView(withId(R.id.addActivityLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewIsDisplayed(){

        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }

    @Test
    public void searchIsDisplayed(){

        onView(withId(R.id.action_search)).check(matches(isDisplayed()));
    }

    @Test
    public void notesTitleIsDisplayedCorrectly(){
        onView(withId(com.google.android.material.R.id.action_bar)).check(matches(hasDescendant(withText("Notes"))));
    }


}
