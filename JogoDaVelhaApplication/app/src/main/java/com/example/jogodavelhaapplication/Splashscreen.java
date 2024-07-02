package com.example.jogodavelhaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Splashscreen extends AppCompatActivity {

    ImageView imgSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        imgSplash = findViewById(R.id.img_splash);
        Glide
                .with(this)
                .load("https://goo.gl/gEgYUd")
                .centerCrop()
                .into(imgSplash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openMenuOptions();
            }
        }, 3000);
    }

    private void openMenuOptions() {
        // para abrir outra tela precisamos criar um INTENT
        Intent intent = new Intent(Splashscreen.this, MenuOptions.class);
        startActivity(intent);
        finish();
    }
}