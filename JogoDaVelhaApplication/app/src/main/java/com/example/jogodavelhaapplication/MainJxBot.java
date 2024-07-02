
package com.example.jogodavelhaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

public class MainJxBot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_jx_bot);

        jogo = new char[3][3];

        mapPosicoes = new HashMap<String, Integer>();
        mapPosicoes.put("a00",R.id.imagem_00);
        mapPosicoes.put("a01",R.id.imagem_01);
        mapPosicoes.put("a02",R.id.imagem_02);
        mapPosicoes.put("a10",R.id.imagem_10);
        mapPosicoes.put("a11",R.id.imagem_11);
        mapPosicoes.put("a12",R.id.imagem_12);
        mapPosicoes.put("a20",R.id.imagem_20);
        mapPosicoes.put("a21",R.id.imagem_21);
        mapPosicoes.put("a22",R.id.imagem_22);
    }

    ImageView imagem1, imagem2, imagem3,imagem4, imagem5, imagem6, imagem7, imagem8, imagem9;
    TextView ganhouPerdeu;
    private HashMap<String, Integer> mapPosicoes;
    int contJogada = 0;
    Random random = new Random();
    char jogo[][];

    public void select(View view) {

        ImageView imagem = (ImageView) view;

        // pega o id da jogada
        String id = view.getResources().getResourceEntryName(imagem.getId()).split("_")[1];

        // inicializando index da matriz
        int i = Integer.parseInt(String.valueOf(id.charAt(0)));
        int j = Integer.parseInt(String.valueOf(id.charAt(1)));

        jogo[i][j] = 'x';

        if(imagem.getContentDescription() == null || "".contentEquals(imagem.getContentDescription())) {
            imagem.setImageResource(R.drawable.x);
            imagem.setContentDescription("X");

            contJogada++;
        }

        if (contJogada == 9) {
            return;
        } else if (contJogada >= 5) {
            GanhouOuPerdeu(montarMatriz());
        }

        ImageView posicao;

        do {
            i = random.nextInt(3);
            j = random.nextInt(3);
        } while (jogo[i][j] != '\u0000');

        // pegando posição
        posicao = findViewById(mapPosicoes.get(String.format("a%d%d", i, j)));

        // seta a foto como a foto do O
        posicao.setImageResource(R.drawable.o);

        // preenche a posição com O na matriz
        jogo[i][j] = 'o';

        contJogada++;
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
        } else if (contJogada == 9){
            ganhouPerdeu.setText("Empate!");
        }
    }
}