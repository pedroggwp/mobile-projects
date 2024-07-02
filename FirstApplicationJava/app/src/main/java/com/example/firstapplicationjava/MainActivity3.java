package com.example.firstapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {

    TextView resultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        resultado = findViewById(R.id.numeroResultado);
    }

    Random random = new Random();

    public void randomMsg(View view) {
        int numAleatorio = random.nextInt(10);
        resultado.setText(String.format("%d", numAleatorio));
    }
}