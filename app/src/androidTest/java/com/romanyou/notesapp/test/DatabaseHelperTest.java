package com.romanyou.notesapp.test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.annotation.SuppressLint;
import android.database.Cursor;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.romanyou.notesapp.controller.NotesController;
import com.romanyou.notesapp.model.DatabaseHelper;
import com.romanyou.notesapp.model.Note;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Testing DatabaseHelper Model
 *
 * @Author Roman Behroz
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class DatabaseHelperTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp() {
        databaseHelper = new DatabaseHelper(InstrumentationRegistry.getTargetContext());

    }

//    @After
//    public void finish() {
//        mDataSource.close();
//    }

    @Test
    public void addData() {
        Note note = new Note("note", "note");
        databaseHelper.addData(note);
        assertEquals(1, databaseHelper.getDataCount());
    }

//    @Test
//    public void testPreConditions() {
//        assertNotNull(mDataSource);
//    }
//
//    @Test
//    public void testShouldAddExpenseType() throws Exception {
//        mDataSource.addData(new Note());
//    }

    @Test
    public void testDeleteAll() {


        databaseHelper.deleteAllData();
        Cursor data = databaseHelper.readAllData();

        assertEquals(0, data.getCount());
    }

    @Test
    public void testDeleteOnlyOne() throws ParseException {
        databaseHelper.addData(new Note("Note1", "Note1"));
        databaseHelper.addData(new Note("Note2", "Note2"));
        List<Note> notes = new ArrayList<>();

        Cursor data = databaseHelper.readAllData();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
        if (data.getCount() == 0) {

        } else {
            while (data.moveToNext()) {


                String dateInString = data.getString(3);
                Date date = formatter.parse(dateInString);

                notes.add(
                        new Note(
                                Integer.valueOf(data.getString(0)),
                                data.getString(1),
                                data.getString(2),
                                date)
                );

            }

            assertThat(databaseHelper.getDataCount(), is(2));


            databaseHelper.deleteData(1);

            if (data.getCount() == 0) {

            } else {
                while (data.moveToNext()) {


                    String dateInString = data.getString(3);
                    Date date = formatter.parse(dateInString);

                    notes.add(
                            new Note(
                                    Integer.valueOf(data.getString(0)),
                                    data.getString(1),
                                    data.getString(2),
                                    date)
                    );


                    assertThat(databaseHelper.getDataCount(), is(1));
                }
            }
        }

    }

}
