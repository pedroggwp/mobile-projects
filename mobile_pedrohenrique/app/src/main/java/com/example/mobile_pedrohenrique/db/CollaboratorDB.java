package com.example.mobile_pedrohenrique.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mobile_pedrohenrique.activity.model.Collaborator;
import com.example.mobile_pedrohenrique.activity.recyclerview.CollaboratorAdapter;
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

public class CollaboratorDB {

    private Map<String, Object> collaborator_id = new HashMap<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollaboratorAdapter adapter;

    public CollaboratorDB(CollaboratorAdapter collaboratorAdapter) {
        this.adapter = collaboratorAdapter;
    }

    public void save(Collaborator collaborator) {
        db.collection("counters").document("collaborator_id").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                int id = 1;
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()) {
                        collaborator_id = document.getData();
                        id = Integer.parseInt(collaborator_id.get("key").toString()) + 1;
                    }
                    collaborator_id.put("key", id);
                    db.collection("counters").document("collaborator_id").set(collaborator_id);
                    //grava a pessoa
                    collaborator.setId(id);
                    db.collection("collaborators").document(String.valueOf(id)).set(collaborator);
                }
            }
        });
    }

    public void findAll(List<Collaborator> list) {
        db.collection("collaborators").addSnapshotListener(new EventListener<QuerySnapshot>() {

            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                list.clear();
                adapter.notifyDataSetChanged();

                for (DocumentSnapshot document : value.getDocuments()) {
                    Collaborator collaborator = document.toObject(Collaborator.class);
                    list.add(collaborator);
                    adapter.notifyItemInserted(list.size());
                }
            }
        });
    }

//    public void listAll(List<Entity> list, Adapter adapter){

//    }
//    public void remove (Entity entity){
//        db.collection("entity").document(String.valueOf(entity.getId())).delete();
//    }

}
