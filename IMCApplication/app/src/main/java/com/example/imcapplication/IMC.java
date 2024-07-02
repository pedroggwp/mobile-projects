package com.example.imcapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class IMC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imc);
    }

    @SuppressLint("SetTextI18n")
    public void calcularIMC(View view) {

        EditText metrosUsuario = findViewById(R.id.metrosUsuario);
        EditText pesoUsuario = findViewById(R.id.pesoUsuario);

        TextView resultoImc = findViewById(R.id.resultadoIMC);

        ImageView image = findViewById(R.id.imagemResultado);


        double metrosUser = Double.parseDouble(metrosUsuario.getText().toString());
        double pesoUser = Double.parseDouble(pesoUsuario.getText().toString());

        double imc = pesoUser / (Math.pow(metrosUser, 2));

        @SuppressLint("DefaultLocale") String imcFormatado = String.format("%.2f", imc);

        if (imc < 18.5) {
            resultoImc.setText("IMC: " + imcFormatado + "\nCLASSIFICAÇÃO: MAGREZA");
            image.setImageResource(R.drawable.pai_incrivel_2);

        } else if (imc >= 18.5 && imc <= 24.9) {
            resultoImc.setText("IMC: " + imcFormatado + "\nCLASSIFICAÇÃO: NORMAL");
            image.setImageResource(R.drawable.pai_incrivel_1);

        } else if (imc >= 25 && imc <= 29.9) {
            resultoImc.setText("IMC: " + imcFormatado + "\nCLASSIFICAÇÃO: SOBREPESO");
            image.setImageResource(R.drawable.pai_incrivel_3);

        } else if (imc >= 30 && imc <= 39.9) {
            resultoImc.setText("IMC: " + imcFormatado + "\nCLASSIFICAÇÃO: OBESIDADE");
            image.setImageResource(R.drawable.pai_incrivel_4);

        } else {
            resultoImc.setText("IMC: " + imcFormatado + "\nCLASSIFICAÇÃO: OBESIDADE GRAVE");
            image.setImageResource(R.drawable.pai_incrivel_5);

        }
    }


}