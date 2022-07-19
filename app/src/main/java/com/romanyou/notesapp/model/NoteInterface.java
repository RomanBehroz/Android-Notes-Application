package com.romanyou.notesapp.model;

import java.util.Date;

/**
 * Note Interface
 *
 * @Author Roman Behroz
 */
public interface NoteInterface {

    /**
     * gets note id
     * @return id
     */
     int getId();

    /**
     * sets note id
     * @param id id
     */
     void setId(int id);

    /**
     * gets note title
     * @return the title
     */
     String getTitle();

    /**
     * sets note title
     * @param title the title
     */
     void setTitle(String title);

    /**
     * gets the note text/content
     * @return
     */
     String getText();

    /**
     * sets note text/content
     * @param text
     */
     void setText(String text);

    /**
     * gets the date on which the note was created
     * @return date of the note
     */
     Date getDate();

    /**
     * sets the date of creation for the note
     * @param date
     */
     void setDate(Date date);
}
