package com.example.jogodavelhaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_options);
    }


    public void goJxJ(View view) {
        Intent intent = new Intent(MenuOptions.this, MainJxJ.class);
        startActivity(intent);
    }

    public void goJxBot(View view) {
        Intent intent = new Intent(MenuOptions.this, MainJxBot.class);
        startActivity(intent);
    }



}