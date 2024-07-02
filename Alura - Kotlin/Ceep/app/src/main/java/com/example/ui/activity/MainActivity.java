package com.example.ui.activity;

import static com.example.ui.activity.NoteConstants.KEY_NOTE;
import static com.example.ui.activity.NoteConstants.REQUEST_CODE_INSERT_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.dao.NoteDAO;
import com.example.model.Note;
import com.example.ui.recyclerview.adapter.NoteListAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteListAdapter adapter;
    private ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        List<Note> collaboratorList = new NoteDAO().allNotes();

        configRecyclerView(collaboratorList);

        TextView buttonAddNote = findViewById(R.id.lista_notas_insere_nota);

        activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == REQUEST_CODE_INSERT_NOTE && result.getData() != null && result.getData().hasExtra(KEY_NOTE)) {
                        Note note = (Note) result.getData().getSerializableExtra(KEY_NOTE);
                        new NoteDAO().insertNote(note);
                        adapter.add(note);
                    }
                }
        );

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent initNoteForm = new Intent(MainActivity.this, NoteFormActivity.class);
                activityResultLauncher.launch(initNoteForm);
            }
        });
    }

    private void configRecyclerView(List<Note> allNotes) {
        RecyclerView notesList = findViewById(R.id.lista_notas_recyclerview);
        adapter = new NoteListAdapter(this, allNotes);
        notesList.setAdapter(adapter);
    }
}