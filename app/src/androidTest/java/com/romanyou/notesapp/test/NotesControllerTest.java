package com.romanyou.notesapp.test;

import static org.junit.Assert.*;

import android.app.Activity;
import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.internal.inject.InstrumentationContext;

import com.romanyou.notesapp.MainActivity;
import com.romanyou.notesapp.controller.NotesController;
import com.romanyou.notesapp.model.Note;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Testing NotesController Controller
 *
 * @Author Roman Behroz
 */
public class NotesControllerTest {

    NotesController controller;

    List<Note> noteList;



    @Before
    public void setUp() throws Exception {



        controller = new NotesController((InstrumentationRegistry.getTargetContext()));

        noteList = new ArrayList<>();
    }

    @Test
    public void getNotesCount() throws ParseException {

        controller.deleteAllNotes();
        assertEquals(0, controller.syncNotes());



    }

    @Test
    public void syncNotes() throws ParseException {

        controller.syncNotes();

        List<Note> list = controller.getNotes();

        List<Note> testList = new ArrayList<>();

        testList.add(new Note("Note1", "First Note"));

        assertEquals(1, list.size());

        assertEquals(testList.get(0).getTitle(), list.get(0).getTitle());

           }

    @Test
    public void updateNote() throws ParseException {

        controller.syncNotes();

        int id = controller.getNotes().get(0).getId();


        controller.updateNote(25, "Title Updated", "Text Updated");



    }

    @Test
    public void addNote() throws ParseException {

        Note note = new Note();
        controller.addNote("note","note");



    }

    @Test
    public void oneNoteAddedGetCount() throws ParseException {
        assertEquals(1, controller.syncNotes());

    }

    @Test
    public void deleteAllNotes() {

        controller.deleteAllNotes();

        assertEquals(0, controller.getNotes().size());
    }
}