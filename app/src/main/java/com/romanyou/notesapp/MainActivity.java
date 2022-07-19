package com.romanyou.notesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.romanyou.notesapp.adapter.RecyclerViewAdapter;
import com.romanyou.notesapp.controller.NotesController;
import com.romanyou.notesapp.model.Note;

import java.text.ParseException;

/**
 * Main Activity(Main Page) of the Notes Application
 * It shows all the Notes as a list in a two column Grid Layout using the RecyclerView
 * The Toolbar has a Search feature to Search the Notes and also an option to Delete all Notes at ones
 * THe most recent Note will be added at the top of the Notes
 *
 * @Author Roman behroz
 */
public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    FloatingActionButton addButton;
    NotesController controller;
    SearchView searchView;
    MenuItem menuItem;
    ImageView empty_imageview;
    TextView no_notes;

    private int notesAmount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarColor();
        setContentView(R.layout.activity_main);

        controller = new NotesController(this);

        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_notes = findViewById(R.id.no_note);

        try {
          notesAmount = controller.syncNotes();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(notesAmount==0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_notes.setVisibility(View.VISIBLE);
        }

        //creating RecyclerView Custom Adapter, passing the Data from controller to the Adapter
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, MainActivity.this, controller.getNotes());
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));

        /**
         * Add Button on Action Listener. Opens the Add Activity Page
         */
        addButton.setOnClickListener(e ->{
            Intent addActivityIntent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(addActivityIntent);
        });


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                manager.hideSoftInputFromWindow(recyclerView.getApplicationWindowToken(), 0);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==1){
            recreate();
        }
    }

    /**
     * Setting ActionBar Background Color
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


    /**
     * Setting my Custom Menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Creating Behaviour/Actions for the Menu Items
     * @param item the Item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        /**
         * Delete Item Selected
         */
        if(item.getItemId() == R.id.delete_all){

            confirmDeleteDialog();
        }

        /**
         * Search Item Selected
         */
        if(item.getItemId() == R.id.action_search){
            menuItem = item;
            searchView = (SearchView) item.getActionView();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {

                    recyclerViewAdapter.getFilter().filter(newText);

                    return false;
                }

            });


        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A popup Confirmation Dialog
     */
    public void confirmDeleteDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Delete all Notes");
        builder.setMessage("Are you sure, you want to delete all Notes?");


        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                controller.deleteAllNotes();
                Toast.makeText(MainActivity.this, "All Notes are deleted", Toast.LENGTH_SHORT).show();
                recreate();
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