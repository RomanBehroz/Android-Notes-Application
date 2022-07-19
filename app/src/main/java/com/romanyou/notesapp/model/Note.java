package com.romanyou.notesapp.model;

import java.time.LocalDate;
import java.util.Date;

/**
 * Class Note is a Model which represents a Note
 *
 * @Author Roman Behroz
 */
public class Note implements NoteInterface{

    /**
     * id of the Note
     */
    private int id;
    /**
     * title of the Note
     */
    private String title;
    /**
     * text or content of the Note
     */
    private String text;
    /**
     * date of creation of the Note
     */
    private Date date;


    /**
     * Empty constructor of Note
     */
    public Note(){

    }

    /**
     * Constructor with two parameters the Date is set automatically to current Date
     * @param title title of the Note
     * @param text text of the Note
     */
    public Note(String title, String text){
        this.title = title;
        this.text = text;
        this.date = new Date();

    }

    /**
     * Constructor with three Parameters. Date is not being set automatically
     * @param title title of the Note
     * @param text text of the Note
     * @param date date of the Note
     */
    public Note(String title, String text, Date date) {
        this.title = title;
        this.text = text;
        this.date = date;

    }

    /**
     * Constructor of the Note with four Parameters
     * @param id id of the Note
     * @param title title of the Note
     * @param text text of the Note
     * @param date date of the Note
     */
    public Note(int id, String title, String text, Date date) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    ////////////////////////////////////////////////////////////////////
    /////////////////////////GETTERS AND SETTERS////////////////////////
    ///////////////////////////////////////////////////////////////////

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
