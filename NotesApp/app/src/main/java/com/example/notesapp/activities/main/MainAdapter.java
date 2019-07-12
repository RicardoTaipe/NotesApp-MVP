package com.example.notesapp.activities.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.notesapp.Model.Note;
import com.example.notesapp.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    List<Note> notes;
    Context context;
    ItemClickListener itemClickListener;

    public MainAdapter(List<Note> notes, Context context, ItemClickListener itemClickListener) {
        this.notes = notes;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.note_item,viewGroup,false);
        return new MainViewHolder(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder mainViewHolder, int position) {
        Note note = notes.get(position);
        mainViewHolder.tv_title.setText(note.getTitle());
        mainViewHolder.tv_note.setText(note.getNote());
        mainViewHolder.tv_date.setText(note.getFormatedDate());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_title,tv_note,tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;
        public MainViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.card_item);
            this.itemClickListener = itemClickListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
