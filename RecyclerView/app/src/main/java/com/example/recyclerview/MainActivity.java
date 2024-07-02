package com.example.recyclerview;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private final List<Note> noteList = new ArrayList<>();
    AdapterNote adapterNote = new AdapterNote(noteList);
    ConnectionDB db = new ConnectionDB();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvNote = findViewById(R.id.rv_note);

        rvNote.setAdapter(adapterNote);

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NoteItemTouchCallback(this.adapterNote));
//        itemTouchHelper.attachToRecyclerView(rvNote);

        rvNote.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        db.getNotesInFirebase(noteList, adapterNote);

        // gera a PK automatica
//        db.collection("notes").document().set(new Note("Renato", "I"));

        // remover
//        db.collection("notes").document("2").delete();

        // recuperar dados
//        db.collection("notes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if (task.isSuccessful()) {
//                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
//                        Note note = document.toObject(Note.class);
//                        noteList.add(note);
//                        adapterNote.notifyItemInserted(noteList.size());
//                    }
//                }
//            }
//        });
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void addNote(View view) {
        Dialog newNote = new Dialog(this);
        newNote.setContentView(R.layout.new_note_dialog);
        Objects.requireNonNull(newNote.getWindow()).setLayout(WRAP_CONTENT, WRAP_CONTENT);
        newNote.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_background));

        Button btnSaveNote = newNote.findViewById(R.id.buttonSaveNote);
        Button btnCancelNote = newNote.findViewById(R.id.buttonCancelNote);

        EditText title = newNote.findViewById(R.id.titleNewNote);
        EditText description = newNote.findViewById(R.id.descriptionNewNote);

        btnSaveNote.setOnClickListener(v -> {
            String titleText = title.getText().toString();
            String descriptionText = description.getText().toString();

            // verifica se os campos de título e descrição estão vazios
            if (!titleText.isEmpty() && !descriptionText.isEmpty()) {
                db.saveNote(new Note(titleText, descriptionText));
                db.getNotesInFirebase(noteList, adapterNote);

                newNote.dismiss();
            } else {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancelNote.setOnClickListener(v -> newNote.dismiss());

        newNote.show();
    }

}