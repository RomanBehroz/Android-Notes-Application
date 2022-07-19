package com.romanyou.notesapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.romanyou.notesapp.controller.NotesController;

/**
 * Add Activity (Adding a Note View Page)
 *
 * @Author Roman Behroz
 */
public class AddActivity extends AppCompatActivity {

    EditText title_input, note_input;
    Button add_button;
    NotesController controller;

    ConstraintLayout addActivityLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarColor();
        setContentView(R.layout.activity_add);
        controller = new NotesController(this);

        addActivityLayout = findViewById(R.id.addActivityLayout);
        title_input = findViewById(R.id.title_input);
        note_input = findViewById(R.id.note_input);
        add_button = findViewById(R.id.save_button);

        add_button.setOnClickListener(e ->{
            if(title_input.length()>0 && note_input.length()>0){
                controller.addNote(title_input.getText().toString().trim(), note_input.getText().toString().trim());

                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }else
            {
                Toast.makeText(AddActivity.this, "Please write something!", Toast.LENGTH_LONG).show();
            }

        });

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

}