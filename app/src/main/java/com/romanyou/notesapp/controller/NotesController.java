package com.romanyou.notesapp.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;

import android.widget.Toast;



import com.romanyou.notesapp.model.DatabaseHelper;
import com.romanyou.notesapp.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class NotesController is a Controller Class for the Notes App
 * It connects the View Layer with the Model Layer and exchanges communication between them
 * NotesController is used to communicate between View and Model
 *
 * @Author Roman Behroz
 */
public class NotesController implements NotesControllerInterface{

   private DatabaseHelper db;

    /**
     * @param notes List of notes
     */
   private List<Note> notes;

    /**
     * @param context the Activity Page
     */
   private Context context;

    /**
     * Controller Constructor
     * @param context Activity Page
     */
    public NotesController(Context context){
        this.context = context;
        db = new DatabaseHelper(context);

        notes = new ArrayList<>();
    }

    /**
     * Contructor
     */
    public NotesController(){

        db = new DatabaseHelper(context);

        notes = new ArrayList<>();
    }

    /**
     *
     * @return Notes list
     */
    public List<Note> getNotes(){

        return notes;
    }

    /**
     * Gets the Data from the Database and Saves it in the Notes list
     * @return returns the number of objects/rows in  the Database
     */
    public int syncNotes() throws ParseException {
        Cursor data = db.readAllData();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy");
        if(data.getCount() == 0){

        }else{
            while(data.moveToNext()){


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
        }

        return data.getCount();
    }

    /**
     * Updates a Note in the Database. Id remains the same
     * @param id id of the Note
     * @param title title of the Note
     * @param text text of the Note
     */
    public void updateNote(int id, String title, String text){
        Note note = new Note();

        note.setId(id);
        note.setTitle(title);
        note.setText(text);

       long result = db.updateData(note);

        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Adds a Note to the Database
     * @param title title of the Note
     * @param text text of the Note
     */
    public void addNote(String title, String text){
            Note note = new Note (title,text);
       long result = db.addData(note);

        if(result == -1)
        {
            Toast.makeText(context, "Failed!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * Deletes a Note from the Database
     * @param id id of the Note that gets deleted
     */
    public void deleteNote(int id){
       long result =  db.deleteData(id);

        if(result == -1){
            Toast.makeText(context, "Failed to delete!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully deleted!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Deletes all the NOtes from the Database
     */
    public void deleteAllNotes(){
        db.deleteAllData();
    }


}
