package com.example.pratica_mobile.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pratica_mobile.model.Collaborator;
import com.example.pratica_mobile.ui.recyclerview.adapter.CollaboratorAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {
    
    Map<String, Object> entity_id = new HashMap<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Database() {
    }

    public void save(Collaborator collaborator) {
        db.collection("counters").document("entity_id").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                int id = 1;
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        entity_id = document.getData();
                        id = Integer.parseInt(entity_id.get("key").toString()) + 1;
                    }
                    entity_id.put("key", id);
                    db.collection("counters").document("entity_id").set(entity_id);
                    // grava a pessoa
                    collaborator.setId(id);
                    db.collection("entity").document(String.valueOf(id)).set(collaborator);
                }
            }
        });
    }

    public void listAll(List<Collaborator> list, CollaboratorAdapter adapter) {
        db.collection("entity").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                list.clear();
                adapter.notifyDataSetChanged();
                for (DocumentSnapshot document : value.getDocuments()) {
                    Collaborator pessoa = document.toObject(Collaborator.class);
                    list.add(pessoa);
                    adapter.notifyItemInserted(list.size());
                }
            }
        });
    }

    public void remove(Collaborator collaborator){
        db.collection("entity").document(String.valueOf(collaborator.getId())).delete();
    }
}
