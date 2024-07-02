package com.example.caraoucoroaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HeadsOrTailsMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heads_or_tails_menu);
    }

    public void normalMode(View view) {
        Intent intent = new Intent(this, NormalMode.class);
        startActivity(intent);
    }

    public void x1Mode(View view) {
        Intent intent = new Intent(this, X1Mode.class);
        startActivity(intent);

    }
}