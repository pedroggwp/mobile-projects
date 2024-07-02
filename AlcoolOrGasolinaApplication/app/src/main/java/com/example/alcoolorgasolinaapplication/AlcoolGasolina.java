package com.example.alcoolorgasolinaapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AlcoolGasolina extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alcool_gasolina);
    }

    public void calcularPreco(View view) {

        EditText inputGasolina = findViewById(R.id.precoGasolina);
        EditText inputAlcool = findViewById(R.id.precoAlcool);

        double valorGasolina = Double.parseDouble(inputGasolina.getText().toString());

        double valorAlcool = Double.parseDouble(inputAlcool.getText().toString());

        TextView resultado = findViewById(R.id.resultado);

        double calculo = (valorAlcool/valorGasolina);

        System.out.println(calculo);

        if (calculo >= 0.7) {
            resultado.setText("Melhor usar gasolina");
        } else {
            resultado.setText("Melhor usar álcool");
        }


    }


}