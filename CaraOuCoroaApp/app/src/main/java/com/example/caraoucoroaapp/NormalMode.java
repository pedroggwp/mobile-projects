package com.example.caraoucoroaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Random;

public class NormalMode extends AppCompatActivity {

    Random random = new Random();
    int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_mode);
    }

    public void startGame(View view) {
        num = random.nextInt(2);

        Bundle data = new Bundle();
        data.putInt("num", num);

        Intent intent = new Intent(this, ResultNormalMode.class);
        intent.putExtras(data);
        startActivity(intent);
        finish();
    }
}