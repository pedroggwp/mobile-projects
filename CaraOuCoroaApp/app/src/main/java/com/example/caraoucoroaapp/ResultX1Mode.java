package com.example.caraoucoroaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class ResultX1Mode extends AppCompatActivity {
    private ImageView imgResult;
    private Random random = new Random();
    private int num, pointsP1 = 0, pointsP2 = 0;
    private TextView pointsPlayer1, pointsPlayer2;
    String p1, p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_x1_mode);

        pointsPlayer1 = findViewById(R.id.points_player_1);
        pointsPlayer2 = findViewById(R.id.points_player_2);

        Bundle getData = getIntent().getExtras();

        p1 = getData.getString("p1").toLowerCase();
        p2 = getData.getString("p2").toLowerCase();

        imgResult = findViewById(R.id.img_result_1);
    }

    public void spinCoin(View view) {
        num = random.nextInt(2);


        if (num == 1) {
            imgResult.setImageResource(R.drawable.moeda_cara);
            imgResult.setContentDescription("Moeda Cara");

            if (p1.equals("cara")) {
                pointsP1++;
            } else {
                pointsP2++;
            }

        } else {
            imgResult.setImageResource(R.drawable.moeda_coroa);
            imgResult.setContentDescription("Moeda Coroa");

            if (p1.equals("coroa")) {
                pointsP1++;
            } else {
                pointsP2++;
            }

        }

        pointsPlayer1.setText(String.valueOf(pointsP1));
        pointsPlayer2.setText(String.valueOf(pointsP2));

    }
}