package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class WelcomeApp extends AppCompatActivity {

    ImageView gifWelcome;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_app);


        Bundle getData = getIntent().getExtras();

        String name = getData.getString("name");

        EditText nameResult = findViewById(R.id.name_result);

        nameResult.setText("Bem vindo " + name + "!");

        gifWelcome = findViewById(R.id.gif_welcome);

        Glide
                .with(this)
                .load("https://media3.giphy.com/media/SX5y0h1Dh5BQVOBwNo/200w.gif?cid=6c09b9524s818aey8fnprdzousl3izfnh0wfgy9l2oievohu&ep=v1_gifs_search&rid=200w.gif&ct=g")
                .centerCrop()
                .into(gifWelcome);
    }
}