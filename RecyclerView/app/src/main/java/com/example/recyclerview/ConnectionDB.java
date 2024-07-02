package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionDB {

    private final FirebaseFirestore db;
    private Map<String, Object> noteId = new HashMap<>();

    public ConnectionDB() {
        // open firebase connection
        this.db = FirebaseFirestore.getInstance();
    }

    public void saveNote(Note note) {

        // create a document -> essa consulta retorna um Snapshot
        db.collection("counters").document("note_id").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                int id = 0;

                if (task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    if (document.exists()) {
                        noteId = document.getData();

                        id = Integer.parseInt( noteId.get("key").toString()) + 1;
                    }

                    noteId.put("key", id);
                    db.collection("counters").document("note_id").set(noteId);

                    // save note
                    note.setId(id);
                    db.collection("notes").document(String.valueOf(id)).set(note);

                    }
                }
        });
    }

    public void getNotesInFirebase(List<Note> noteList, AdapterNote adapterNote) {
        db.collection("notes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                noteList.clear();
                adapterNote.notifyDataSetChanged();

                for (DocumentSnapshot document : value.getDocuments()) {
                    Note note = document.toObject(Note.class);
                    noteList.add(note);
                    adapterNote.notifyItemInserted(noteList.size());
                }
            }
        });
    }

    public void removeFromFirebase(int id) {
        db.collection("notes").document(String.valueOf(id)).delete();
    }

    public void removeFromFirebase(int id, Context context) {
        db.collection("notes").document(String.valueOf(id)).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Removido com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
