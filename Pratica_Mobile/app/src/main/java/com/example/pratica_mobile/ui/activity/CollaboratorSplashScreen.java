package com.example.pratica_mobile.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pratica_mobile.R;

@SuppressLint("CustomSplashScreen")
public class CollaboratorSplashScreen extends AppCompatActivity {

    private ImageView splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborator_splash_screen);

        splashScreen = findViewById(R.id.picpayGif);

        Glide
                .with(this)
                .load("https://jfhomecarebr.com.br/wp-content/uploads/2023/03/jfhomecarelogo.png")
                .into(splashScreen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(CollaboratorSplashScreen.this, CollaboratorActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}