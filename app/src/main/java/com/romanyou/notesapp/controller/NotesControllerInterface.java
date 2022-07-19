package com.romanyou.notesapp.controller;

import com.romanyou.notesapp.model.Note;

import java.text.ParseException;
import java.util.List;

/**
 * Notes Controller Interface
 *
 * @Author Roman Behroz
 */
public interface NotesControllerInterface {

    /**
     * Adds a Note to the Database
     * @param title title of the Note
     * @param text text of the Note
     */
    void addNote(String title, String text);

    /**
     * Updates a Note in the Database. Id remains the same
     * @param id id of the Note
     * @param title title of the Note
     * @param text text of the Note
     */
    void updateNote(int id, String title, String text);

    /**
     * Deletes a Note from the Database
     * @param id id of the Note that gets deleted
     */
    void deleteNote(int id);

    /**
     * Deletes all the NOtes from the Database
     */
    void deleteAllNotes();

    /**
     * Gets the Data from the Database and Saves it in the Notes list
     * @return returns the number of objects/rows in  the Database
     */
    int syncNotes() throws ParseException;

    /**
     *Gets the List of the Notes
     * @return Notes list
     */
    List<Note> getNotes();


}
