package com.romanyou.notesapp.espresso;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.romanyou.notesapp.MainActivity;
import com.romanyou.notesapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


/**
 * Integration Tests, Espresso Tests for Recycler View
 *
 * @Author Roman Behroz
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recyclerOpenItemAndCheckValuesTest(){
        Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        Espresso.onView(withId(R.id.title_input_edit)).check(matches(withText("Note1")));
        Espresso.onView(withId(R.id.note_input_edit)).check(matches(withText("First Note")));
    }

    @Test
    public void recyclerDeleteItemTest(){
        Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.action_delete)).perform(click());

        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.mainActivityLayout)).check(matches(isDisplayed()));

    }

    @Test
    public void recyclerItemCountTest(){
        RecyclerView recyclerView = mainActivityActivityTestRule.getActivity().findViewById(R.id.recyclerView);

        int amount = recyclerView.getAdapter().getItemCount();

        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(amount-1));
    }

    @Test
    public void openNoteEditNoteAndSaveIt(){
        Espresso.onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.title_input_edit)).perform(click());
        onView(withId(R.id.title_input_edit)).perform(typeText(" Espresso Test"));
        onView(withId(R.id.save_button_edit)).perform(click());

    }

    @Test
    public void writeNewNoteAndSaveIt(){
        onView(withId(R.id.add_button)).perform(click());
        onView(withId(R.id.title_input)).perform(typeText("Note Title Test"));
        onView(withId(R.id.note_input)).perform(typeText("Note Text Test"));
        onView(withId(R.id.save_button)).perform(click());

        onView(withId(R.id.mainActivityLayout)).check(matches(isDisplayed()));

    }


}
