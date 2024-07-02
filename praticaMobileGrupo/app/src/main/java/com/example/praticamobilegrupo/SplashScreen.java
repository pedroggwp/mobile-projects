package com.example.praticamobilegrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashScreen extends AppCompatActivity {

    private ImageView splashGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashGif = findViewById(R.id.splashGif);

        Glide
                .with(this)
                    .load("https://media.tenor.com/T7R2IDpV6X0AAAAi/primeira-animacao-receita-federal.gif")
                .into(splashGif);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, Tela2.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}