package com.example.caraoucoroaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ResultNormalMode extends AppCompatActivity {

    ImageView imgResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_normal_mode);

        imgResult = findViewById(R.id.img_result);


        Bundle getNum = getIntent().getExtras();
        int num = getNum.getInt("num");

        System.out.println(num);

        if (num == 1) {
            imgResult.setImageResource(R.drawable.moeda_cara);
            imgResult.setContentDescription("Moeda Cara");
        } else {
            imgResult.setImageResource(R.drawable.moeda_coroa);
            imgResult.setContentDescription("Moeda Coroa");

        }
    }

    public void goBack(View view) {
        Intent intent = new Intent(this, NormalMode.class);
        startActivity(intent);
        finish();
    }
}