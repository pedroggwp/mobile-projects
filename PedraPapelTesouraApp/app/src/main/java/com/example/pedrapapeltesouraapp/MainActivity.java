package com.example.pedrapapeltesouraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Random random = new Random();
    private int numRandom;
    private ImageView escolhaBot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        escolhaBot = findViewById(R.id.escolha_bot);
    }

    public void jogar(View view) {
        ImageView image = (ImageView) view;

        String escolhaPlayer = view.getResources().getResourceEntryName(image.getId());
        String escolhaBotStr;

        numRandom = random.nextInt(3); // 0 1 2

        if (numRandom == 0) {
            escolhaBot.setImageResource(R.drawable.pedra);
            escolhaBotStr = "pedra";
        } else if (numRandom == 1) {
            escolhaBot.setImageResource(R.drawable.papel);
            escolhaBotStr = "papel";
        } else {
            escolhaBot.setImageResource(R.drawable.tesoura);
            escolhaBotStr = "tesora";
        }

        ganhouOuPerdeu(escolhaPlayer, escolhaBotStr);
    }

    private void ganhouOuPerdeu(String escolhaPlayer, String escolhaBot) {
        if (escolhaPlayer.equals(escolhaBot)) {
            Toast.makeText(MainActivity.this, "Houve um empate!", Toast.LENGTH_SHORT).show();
        } else if (escolhaPlayer.equals("pedra") && escolhaBot.equals("tesoura")) {
            Toast.makeText(MainActivity.this, "Você ganhou!", Toast.LENGTH_SHORT).show();
        } else if (escolhaPlayer.equals("papel") && escolhaBot.equals("pedra")) {
            Toast.makeText(MainActivity.this, "Você ganhou!", Toast.LENGTH_SHORT).show();
        } else if (escolhaPlayer.equals("tesoura") && escolhaBot.equals("papel")) {
            Toast.makeText(MainActivity.this, "Você ganhou!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Você perdeu!", Toast.LENGTH_SHORT).show();
        }

    }

    public void reiniciar(View view) {
        escolhaBot.setImageResource(R.drawable.padrao);
    }
}