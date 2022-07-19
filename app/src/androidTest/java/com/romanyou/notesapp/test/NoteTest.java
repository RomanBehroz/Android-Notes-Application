package com.romanyou.notesapp.test;

import static org.junit.Assert.*;

import com.romanyou.notesapp.model.Note;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Testing Note Class Model
 *
 * @Author Roman Behroz
 */
public class NoteTest {

    private Note note;

    @Before
    public void setUp() throws Exception {

        note = new Note(1, "Note1", "First Note", new Date());
    }



    @Test
    public void getId() {

        assertEquals(1, note.getId());
    }

    @Test
    public void setId() {

        note.setId(2);
        assertEquals(2, note.getId());
    }

    @Test
    public void getTitle() {
        assertEquals("Note1", note.getTitle());

    }

    @Test
    public  void setTitle() {

        note.setTitle("Note2");
        assertEquals("Note2", note.getTitle());
    }

    @Test
    public   void getText() {
        assertEquals("First Note", note.getText());
    }

    @Test
    public  void setText() {

        note.setText("Second Note");
        assertEquals("Second Note", note.getText());
    }

    @Test
    public  void getDate() {

        assertEquals(new Date(), note.getDate());

    }

    @Test
    public  void setDate() {
        note.setDate(new Date());
        assertEquals(new Date(), note.getDate());
    }
}