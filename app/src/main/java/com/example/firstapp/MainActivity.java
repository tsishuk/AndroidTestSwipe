package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Note> notes;
    RecyclerView rvNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notes = new ArrayList<>();
        notes.add(new Note("One", "This is one", 1));
        notes.add(new Note("Two", "This is two", 2));
        notes.add(new Note("Three", "This is trheerer", 3));
        notes.add(new Note("One", "This is one", 1));
        notes.add(new Note("Two", "This is two", 2));
        notes.add(new Note("Three", "This is trheerer", 3));
        notes.add(new Note("One", "This is one", 1));
        notes.add(new Note("Two", "This is two", 2));
        notes.add(new Note("Three", "This is trheerer", 3));


        rvNotes = findViewById(R.id.rvNotes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        final NotesAdapter adapter = new NotesAdapter(notes);

        // Устанавливаем свой слушатель на нажатия элементов списка
        adapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onSingleNoteClick(int position) {
                Toast.makeText(MainActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongNoteClick(int position) {
                notes.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        // Устанавливаем слушатель на свайпы элементов
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                notes.remove(viewHolder.getAdapterPosition());
                adapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(rvNotes);
        rvNotes.setAdapter(adapter);
        rvNotes.setLayoutManager(layoutManager);
    }
}