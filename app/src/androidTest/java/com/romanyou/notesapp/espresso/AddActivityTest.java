package com.romanyou.notesapp.espresso;


import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
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
 * Testing AddActivity View
 *
 * @Author Roman Behroz
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddActivityTest {

    @Rule
    public ActivityTestRule<AddActivity> addActivityActivityTestRule =
            new ActivityTestRule<>(AddActivity.class);

    @Test
    public void saveButtonIsDisplayed(){
        onView(withId(R.id.save_button)).check(matches(isDisplayed()));
        onView(withId(R.id.save_button)).check(matches(isClickable()));

    }

    @Test
    public void titleTextFieldIsDisplayed(){
        onView(withId(R.id.title_input)).check(matches(isDisplayed()));
        onView(withId(R.id.title_input)).check(matches(isClickable()));

    }

    @Test
    public void noteTextFieldIsDisplayed(){
        onView(withId(R.id.note_input)).check(matches(isDisplayed()));
        onView(withId(R.id.note_input)).check(matches(isClickable()));

    }

    @Test
    public void writeNoteAndSaveIt(){
        onView(withId(R.id.title_input)).perform(click()).perform(typeText("Note Title Test"));
        onView(withId(R.id.note_input)).perform(click()).perform(typeText("Note Text Test"));
        onView(withId(R.id.save_button)).perform(click());
        onView(withId(R.id.mainActivityLayout)).check(matches(isDisplayed()));

    }
}
