package com.romanyou.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.romanyou.notesapp.controller.NotesController;

import java.sql.SQLOutput;

/**
 * Edit Activity (Editing a Note View Page)
 *
 * @Author Roman Behroz
 */
public class EditActivity extends AppCompatActivity {

    EditText title_input, text_input;
    Button save_button;
    String title, text;
    int id;

    ConstraintLayout editActivityLayout;

    NotesController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        controller = new NotesController(this);

        editActivityLayout = findViewById(R.id.editActivityLayout);

;

        setActionBarColor();

        title_input = findViewById(R.id.title_input_edit);
        text_input = findViewById(R.id.note_input_edit);
        save_button = findViewById(R.id.save_button_edit);

        getMainActivityIntentData();

        save_button.setOnClickListener(e ->{
            controller.updateNote(id, title_input.getText().toString().trim(), text_input.getText().toString().trim());
        });

    }

    public void getMainActivityIntentData(){
         if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("text")){
             id = Integer.valueOf(getIntent().getStringExtra("id"));
           title = getIntent().getStringExtra("title");
           text = getIntent().getStringExtra("text");

           title_input.setText(title);
           text_input.setText(text );
         }
    }

    /**
     * Setting ActionBar Color
     */
    protected void setActionBarColor(){
        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and getting Color
        ColorDrawable colorDrawable
                = new ColorDrawable(getResources().getColor(R.color.bg_purple));
        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setElevation(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.notes_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.action_delete){

            confirmDeleteDialog();


        }


        return super.onOptionsItemSelected(item);
    }


    public void confirmDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setTitle("Delete " + title);
        builder.setMessage("Are you sure, you want to delete " + title + "?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.deleteNote(id);

                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.create().show();
    }

}