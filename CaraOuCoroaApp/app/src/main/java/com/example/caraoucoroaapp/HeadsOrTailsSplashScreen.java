package com.example.caraoucoroaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

@SuppressLint("CustomSplashScreen")
public class HeadsOrTailsSplashScreen extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(HeadsOrTailsSplashScreen.this, HeadsOrTailsMenu.class);
            startActivity(intent);
            finish();
        }, 2000);

    }
}