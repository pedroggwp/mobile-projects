package com.example.jogodavelhaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainJxJ extends AppCompatActivity {

    ImageView imagem1, imagem2, imagem3,imagem4, imagem5, imagem6, imagem7, imagem8, imagem9;
    TextView ganhouPerdeu;

    int cont = 0;
    int contPartida = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jx_j);
    }

    public void select(View view) {
        ImageView imagem = (ImageView) view;
        if(imagem.getContentDescription() == null || "".equals(imagem.getContentDescription())) {
            if (cont % 2 == 0) {
                imagem.setImageResource(R.drawable.x);
                imagem.setContentDescription("X");
            } else {
                imagem.setImageResource(R.drawable.o);
                imagem.setContentDescription("O");
            }
            cont++;
            contPartida++;
        }
        GanhouOuPerdeu(montarMatriz());

    }

    public String[][] montarMatriz() {
        String [][] matriz = new String[3][3];
        imagem1 = findViewById(R.id.imagem_00);
        imagem2 = findViewById(R.id.imagem_01);
        imagem3 = findViewById(R.id.imagem_02);
        imagem4 = findViewById(R.id.imagem_10);
        imagem5 = findViewById(R.id.imagem_11);
        imagem6 = findViewById(R.id.imagem_12);
        imagem7 = findViewById(R.id.imagem_20);
        imagem8 = findViewById(R.id.imagem_21);
        imagem9 = findViewById(R.id.imagem_22);

        matriz[0][0] = (String) imagem1.getContentDescription();
        matriz[0][1] = (String) imagem2.getContentDescription();
        matriz[0][2] = (String) imagem3.getContentDescription();
        matriz[1][0] = (String) imagem4.getContentDescription();
        matriz[1][1] = (String) imagem5.getContentDescription();
        matriz[1][2] = (String) imagem6.getContentDescription();
        matriz[2][0] = (String) imagem7.getContentDescription();
        matriz[2][1] = (String) imagem8.getContentDescription();
        matriz[2][2] = (String) imagem9.getContentDescription();

        return matriz;
    }

    public void GanhouOuPerdeu(String [][] matriz){
        ganhouPerdeu = findViewById(R.id.ganhouPerdeu);

        if(matriz[0][0] != null && matriz[0][0].equals(matriz[0][1]) && matriz[0][1].equals(matriz[0][2])){
            ganhouPerdeu.setText(matriz[0][0] + " ganhou!!");
        } else if(matriz[1][0] != null && matriz[1][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[1][2])) {

            ganhouPerdeu.setText(matriz[1][0] + " ganhou!!");
        } else if(matriz[2][0] != null && matriz[2][0].equals(matriz[2][1]) && matriz[2][1].equals(matriz[2][2])){

            ganhouPerdeu.setText(matriz[2][0] + " ganhou!!");
        } else if(matriz[0][0] != null && matriz[0][0].equals(matriz[0][1]) && matriz[0][1].equals(matriz[0][2])){

            ganhouPerdeu.setText(matriz[0][0] + " ganhou!!");
        } else if(matriz[1][0] != null && matriz[1][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[1][2])){

            ganhouPerdeu.setText(matriz[1][0] + " ganhou!!");
        } else if(matriz[2][0] != null && matriz[2][0].equals(matriz[2][1]) && matriz[2][1].equals(matriz[2][2])){

            ganhouPerdeu.setText(matriz[2][0] + " ganhou!!");
        } else if(matriz[0][0] != null && matriz[0][0].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][2])){

            ganhouPerdeu.setText(matriz[0][0] + " ganhou!!");
        } else if(matriz[0][2] != null && matriz[0][2].equals(matriz[1][1]) && matriz[1][1].equals(matriz[2][0])) {

            ganhouPerdeu.setText(matriz[0][2] + " ganhou!!");
        } else if (contPartida == 9){
            ganhouPerdeu.setText("Empate!");
        }
    }
}