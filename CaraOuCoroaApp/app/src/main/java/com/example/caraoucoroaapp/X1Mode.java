package com.example.caraoucoroaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Random;

public class X1Mode extends AppCompatActivity {

    private Spinner spinner, spinner1;
//    private Random random = new Random();
//    private int num;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x1_mode);

        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);

        setupSpinner(R.id.spinner);
        setupSpinner(R.id.spinner1);
    }

    private void setupSpinner(int spinnerId) {
        Spinner spinner = findViewById(spinnerId);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opcoes_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void startGame(View view) {
        if (spinner.getSelectedItem().equals(spinner1.getSelectedItem())) {
            Toast.makeText(X1Mode.this, "As escolhas dos jogadores devem ser diferentes", Toast.LENGTH_SHORT).show();
        } else {

            Bundle data = new Bundle();
            data.putString("p1", spinner.getSelectedItem().toString());
            data.putString("p2", spinner1.getSelectedItem().toString());

            Intent intent = new Intent(this, ResultX1Mode.class);
            intent.putExtras(data);
            startActivity(intent);
        }
    }

}
