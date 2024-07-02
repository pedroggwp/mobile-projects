package com.example.mobile_pedrohenrique.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.mobile_pedrohenrique.R;

@SuppressLint("CustomSplashScreen")
public class CollaboratorSplashScreen extends AppCompatActivity {

    private ImageView splashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborator_splash_screen);

        splashScreen = findViewById(R.id.collaborator_image);

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