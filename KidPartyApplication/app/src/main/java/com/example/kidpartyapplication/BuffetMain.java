package com.example.kidpartyapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class BuffetMain extends AppCompatActivity {

    private final List<Child> childList = new ArrayList<>();
    private AdapterChild adapterChild;
    private static final int ADD_CHILD_REQUEST = 1;
    private ActivityResultLauncher<Intent> addChildLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buffet_main);
        RecyclerView rvView = findViewById(R.id.rvChild);

        adapterChild = new AdapterChild(childList);
        rvView.setAdapter(adapterChild);
        rvView.setLayoutManager(new LinearLayoutManager(this));

        addChildLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            String childName = data.getStringExtra("childName");
                            Child newChild = new Child(childName);
                            childList.add(newChild);
                            adapterChild.notifyItemInserted(childList.size());
                        }
                    }
                });
    }

    public void addChild(View view) {
        Intent intent = new Intent(this, ChildForm.class);
        addChildLauncher.launch(intent);
    }

}