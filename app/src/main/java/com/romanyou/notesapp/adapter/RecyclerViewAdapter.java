package com.romanyou.notesapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.romanyou.notesapp.EditActivity;
import com.romanyou.notesapp.R;
import com.romanyou.notesapp.model.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Custom RecyclerViewAdapter for RecyclerView Component Usage
 * The class is used in order to use the RecyclerView
 * This class binds the Model Data to the ReyclerView
 *
 * @Author Roman Behroz
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private Activity activity;

    private List<Note> notes;
    private List<Note> notesAll;
    private int n = 0;

    CardView cardView;

    /**
     * Constructor
     * Receives the Notes from the Controller and Sets it on RecyclerView
     * @param context Activity Page
     * @param notes list of Notes
     */
    public RecyclerViewAdapter(Activity activity, Context context, List<Note> notes){
        this.activity = activity;
        this.context = context;
        this.notes = notes;
        this.notesAll = new ArrayList<>(notes);

    }


    /**
     * Creates the ViewHolder and sets custom row design for RecyclerView Rows
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    /**
     * Binds the Data from Notes list to the EditText views in order to present it on the screen
     * @param holder
     * @param position position of the Note in the List
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy");
        String date = sdf.format(notes.get(position).getDate());

        holder.note_title_txt.setText(String.valueOf(notes.get(position).getTitle()));
        holder.note_text_txt.setText(String.valueOf(notes.get(position).getText()));
        holder.note_date_txt.setText(date);

        /**
         * calls openNote when a Note is clicked on
         */
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openNote(position);
            }
        });

        /**
         * calls openNote when a Note is clicked on
         */
        holder.note_text_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNote(position);
            }
        });


        if(n==0){
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_bg_2));
            n=1;
        }else{
            cardView.setCardBackgroundColor(context.getResources().getColor(R.color.card_bg_1));
            n=0;
        }
        //setting card background color


    }

    /**
     * Opens the Note Edit Activity and passes the Data it
     * @param position
     */
    public void openNote(int position){
        Intent editActivityIntent = new Intent(context, EditActivity.class);
        editActivityIntent.putExtra("id", String.valueOf(notes.get(position).getId()));
        editActivityIntent.putExtra("title", String.valueOf(notes.get(position).getTitle()));
        editActivityIntent.putExtra("text", String.valueOf(notes.get(position).getText()));

        activity.startActivityForResult(editActivityIntent, 1);
    }

    /**
     *
     * @return number of Notes in the List
     */
    @Override
    public int getItemCount() {
        return notes.size();
    }
            /**
             * INNER CLASS MYVIEWHOLDER
             * Creates the TextViews and sets the IDs
             */
            public class MyViewHolder extends RecyclerView.ViewHolder {

                TextView note_title_txt, note_text_txt, note_date_txt;

                LinearLayout mainLayout;

                //MyViewHolder Constructor
                public MyViewHolder(@NonNull View itemView) {
                    super(itemView);
                    note_title_txt = itemView.findViewById(R.id.note_title_txt);
                    note_text_txt = itemView.findViewById(R.id.note_text_txt);
                    note_date_txt = itemView.findViewById(R.id.note_date_txt);
                    cardView = itemView.findViewById(R.id.card_view);
                    mainLayout = itemView.findViewById(R.id.mainLayout);

                }


            }



    @Override
    public Filter getFilter() {
        return filterNotes;
    }

    /**
     * Filtering helps to Search the RecyclerView objects(Notes)
     */
  Filter filterNotes = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Note> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(notesAll);
            } else {
                for (Note note : notesAll) {
                    if (note.getTitle().toLowerCase().contains(charSequence.toString().toLowerCase()) ||
                            note.getText().toLowerCase().contains(charSequence.toString().toLowerCase())
                    ) {
                        filteredList.add(note);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }


        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notes.clear();
            notes.addAll((Collection<? extends Note>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
