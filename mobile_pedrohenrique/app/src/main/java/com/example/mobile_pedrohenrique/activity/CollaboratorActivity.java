package com.example.mobile_pedrohenrique.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pedrohenrique.R;
import com.example.mobile_pedrohenrique.activity.model.Collaborator;
import com.example.mobile_pedrohenrique.activity.recyclerview.CollaboratorAdapter;
import com.example.mobile_pedrohenrique.db.CollaboratorDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollaboratorActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddService;
    private ActivityResultLauncher<Intent> activityResultLauncher;
    private CollaboratorAdapter collaboratorAdapter;
    private CollaboratorDB db = new CollaboratorDB(collaboratorAdapter);
    private List<Collaborator> collaborators = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborator);

        db.save(new Collaborator(32332, new Date()));
         db.findAll(collaborators);

        RecyclerView collaboratorList = findViewById(R.id.collaborator_recyclerview);

        // config recyclerview
        collaboratorAdapter = new CollaboratorAdapter(CollaboratorActivity.this, collaborators);
        collaboratorList.setAdapter(collaboratorAdapter);
        collaboratorList.setLayoutManager(new LinearLayoutManager(this));

        buttonAddService = findViewById(R.id.floatingActionButton);

//        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                result -> {
//                    if (result.getResultCode() == 1 && result.getData() != null && result.getData().hasExtra("collaborator")) {
//                        Collaborator collaborator = (Collaborator) result.getData().getSerializableExtra("collaborator");
////                        new CollaboratorDAO().insertCollaborator(collaborator);
//                        collaboratorAdapter.add(collaborator);
//                    }
//                });

        buttonAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent initNoteForm = new Intent(CollaboratorActivity.this, CollaboratorForm.class);
//                activityResultLauncher.launch(initNoteForm);
            }
        });
    }
}