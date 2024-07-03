package com.example.ui.activity;

import static com.example.ui.activity.NoteConstants.KEY_NOTE;
import static com.example.ui.activity.NoteConstants.REQUEST_CODE_INSERT_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.ui.recyclerview.adapter.listener.OnItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteListAdapter adapter;
    private ActivityResultLauncher<Intent> noteFormLauncher;
    private ActivityResultLauncher<Intent> editNoteLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        List<Note> allNotes = getAllNotes();

        configRecyclerView(allNotes);

        TextView buttonAddNote = findViewById(R.id.lista_notas_insere_nota);

        noteFormLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

                result -> {
                    if (result.getResultCode() == REQUEST_CODE_INSERT_NOTE && result.getData() != null && result.getData().hasExtra(KEY_NOTE)) {
                        Note note = (Note) result.getData().getSerializableExtra(KEY_NOTE);
                        new NoteDAO().insertNote(note);
                        adapter.add(note);
                    }
                }
        );

        editNoteLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),

                result -> {
                    if (result.getResultCode() == REQUEST_CODE_INSERT_NOTE && result.getData() != null && result.getData().hasExtra(KEY_NOTE) && result.getData().hasExtra("position")) {
                        Note receivedNote = (Note) result.getData().getSerializableExtra(KEY_NOTE);
                        int receivedPosition = result.getData().getIntExtra("position", -1);
                        new NoteDAO().updateNote(receivedPosition, receivedNote);
                        adapter.update(receivedPosition, receivedNote);
                    }
                }
        );

        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent initNoteForm = new Intent(MainActivity.this, NoteFormActivity.class);
                noteFormLauncher.launch(initNoteForm);
            }
        });
    }

    private List<Note> getAllNotes() {
        NoteDAO dao = new NoteDAO();

        for(int i = 0; i < 5; i++) {
            dao.insertNote((new Note("Title " + (i + 1), "Description " + (i + 1))));
        }

        return dao.allNotes();
    }

    private void configRecyclerView(List<Note> allNotes) {
        RecyclerView recyclerView = findViewById(R.id.lista_notas_recyclerview);
        configAdapter(allNotes, recyclerView);
    }

    private void configAdapter(List<Note> allNotes, RecyclerView recyclerView) {
        adapter = new NoteListAdapter(this, allNotes);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(Note note, int position) {
                Intent openFormWithNote = new Intent(MainActivity.this, NoteFormActivity.class);

                openFormWithNote.putExtra(KEY_NOTE, note);
                openFormWithNote.putExtra("position", position);

                editNoteLauncher.launch(openFormWithNote);
            }
        });
    }
}