package com.example.lembreteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText textInput;
    private ListView listView;
    private ArrayList<String> listValues;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textInput = findViewById(R.id.text_input);
        listView = findViewById(R.id.list_view);

        listValues = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listValues);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                Toast.makeText(getApplicationContext(),
//                                "Click ListItem posicao: " + position + " - " +
//                                        listValues.get(position), Toast.LENGTH_LONG)
//                        .show();
//            }
//        });
    }

    public void addItem(View view) {
        listValues.add(String.valueOf(textInput.getText()));
//        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
    }
}