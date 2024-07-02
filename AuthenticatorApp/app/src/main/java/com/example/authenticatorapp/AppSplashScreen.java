package com.example.authenticatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

@SuppressLint("CustomSplashScreen")
public class AppSplashScreen extends AppCompatActivity {

    ImageView gifSplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_splash_screen);

        gifSplashScreen = findViewById(R.id.gif_splash_screen);

        Glide
                .with(this)
                .load("https://media3.giphy.com/media/SX5y0h1Dh5BQVOBwNo/200w.gif?cid=6c09b9524s818aey8fnprdzousl3izfnh0wfgy9l2oievohu&ep=v1_gifs_search&rid=200w.gif&ct=g")
                .centerCrop()
                .into(gifSplashScreen);

        Handler handler = new Handler();
        handler.postDelayed(this::openApp, 2000);
    }

    public void openApp() {
        // intent para abrir a tela principal do app
        Intent intent = new Intent(this, AuthenticatorApp.class);
        startActivity(intent);
        finish();
    }
}