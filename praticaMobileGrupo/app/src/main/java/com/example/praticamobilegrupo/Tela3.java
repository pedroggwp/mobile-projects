package com.example.praticamobilegrupo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Tela3 extends AppCompatActivity {
    TextView impostoTextView;
    String anualOuMensal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela3);
        impostoTextView = findViewById(R.id.impostoTextView);
        Bundle dados = getIntent().getExtras();
        double input = dados.getDouble("salario");
        anualOuMensal = dados.getString("anualOuMensal");
        double imposto = 0;
        String mensagemPadrao;
        if (anualOuMensal.equals("Mensal")) {
            mensagemPadrao = "O valor do seu imposto mensal deverá ser ";
        } else {
            mensagemPadrao = "O valor anual do seu imposto anual deverá ser ";
        }
        if (input <= 1903.98) {
            impostoTextView.setText(mensagemPadrao + "R$ 0,00");
        } else if (input <= 2826.85) {
            imposto = (input * 7.5 / 100) - 142.80;
        } else if (input <= 3751.05) {
            imposto = (input * 15 / 100) - 354.80;
        } else if (input <= 4664.68) {
            imposto = (input * 22.5) - 636.13;
        } else {
            imposto = (input * 27.5) - 869.36;
        }
        if (anualOuMensal.equals("Anual")) {
            imposto *= 12;
        }
        impostoTextView.setText(mensagemPadrao + "R$ " + String.format("%.2f", imposto));
    }

    public void tabelaDeCalculo(View view) {
        Intent intent;

        if (anualOuMensal.equals("Anual")) {
            intent = new Intent(this, Tela4_2.class);
        } else {
            intent = new Intent(this, Tela4.class);
        }
        startActivity(intent);
        finish();
    }
}