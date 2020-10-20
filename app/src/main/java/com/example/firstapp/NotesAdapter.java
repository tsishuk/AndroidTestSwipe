package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    ArrayList<Note> notesInAdapter;
    OnNoteClickListener onNoteClickListener;

    public void setOnNoteClickListener(OnNoteClickListener onNoteClickListener) {
        this.onNoteClickListener = onNoteClickListener;
    }

    public NotesAdapter(ArrayList<Note> notesInAdapter) {
        this.notesInAdapter = notesInAdapter;
    }

    interface OnNoteClickListener{
        void onSingleNoteClick(int position);
        void onLongNoteClick(int position);
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new NotesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note note = notesInAdapter.get(position);
        int colorId = note.getColorId();
        holder.tvName.setText(note.getName());
        holder.tvDescription.setText(note.getDescritpion());
        int color;

        switch (colorId) {
            case 1:
                color = holder.itemView.getResources().getColor(android.R.color.holo_green_light);
                break;
            case 2:
                color = holder.itemView.getResources().getColor(android.R.color.holo_blue_light);
                break;
            case 3:
                color = holder.itemView.getResources().getColor(android.R.color.holo_orange_light);
                break;
            default:
                color = holder.itemView.getResources().getColor(android.R.color.holo_red_dark);
                break;
        }
        holder.tvName.setBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return notesInAdapter.size();
    }



    class NotesViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvDescription;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.textViewName);
            tvDescription = itemView.findViewById(R.id.textViewDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNoteClickListener != null)
                        onNoteClickListener.onSingleNoteClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onNoteClickListener != null)
                        onNoteClickListener.onLongNoteClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
