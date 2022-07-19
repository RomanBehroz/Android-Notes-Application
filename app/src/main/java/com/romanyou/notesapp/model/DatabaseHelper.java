package com.romanyou.notesapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * DatabaseHelper Class helps to use the SQLite Database
 * All Notes are being stored in this Database
 *
 * @Author Roman Behroz
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Notes.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "note_title";
    private static final String COLUMN_TEXT = "note_text";
    private static final String COLUMN_DATE = "note_date";



    /**
     * Constructor of DatabaseHelper
     * @param context
     */
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    /**
     * Creates the Table in the Database
     * @param db SQLiteDatabase instance
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT NOT NULL, " +
                        COLUMN_TEXT + " TEXT NOT NULL, " +
                        COLUMN_DATE + " TEXT NOT NULL);";
        db.execSQL(query);
    }

    /**
     * Upgrades the Table in the Database, deletes the existing one and creates a new one
     * @param db
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     * Adds Data to the Database Table
     * @param note the Data(Note) that will be added
     */
    public long addData(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = sdf.format(note.getDate());

        cv.put(COLUMN_TITLE, note.getTitle());
        cv.put(COLUMN_TEXT, note.getText());
        cv.put(COLUMN_DATE, date);

        long result = db.insert(TABLE_NAME, null, cv);

        return result;
    }

    /**
     * Getting all the data from the Database
     * @return the Data
     */
    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = null;

        if(db != null){
            data = db.rawQuery(query, null);
        }
        System.out.println("DATABASE COUNT" + data.getCount());
        return data;
    }

    /**
     * Updates the Data(Note) in the Database Table
     * @param note the Data that will be updated
     */
    public long updateData(Note note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE, note.getTitle());
        cv.put(COLUMN_TEXT, note.getText());

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(note.getId())});

        return result;
    }

    /**
     * Deletes a Data from the Database Table
     * @param id the Data id
     */
    public long deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        long result =  db.delete(TABLE_NAME, "_id=?", new String[]{String.valueOf(id)});

      return result;
    }

    /**
     * Deletes All the Data from the Database
     */
    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    /**
     * counts the number of objects in the database table
     * @return amount
     */
    public int getDataCount(){
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = null;

        if(db != null){
            data = db.rawQuery(query, null);
        }

        return data.getCount();
    }


}
