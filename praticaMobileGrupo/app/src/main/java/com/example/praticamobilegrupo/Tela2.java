package com.example.praticamobilegrupo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Tela2 extends AppCompatActivity {

    private TextInputEditText salarioInput;
    private RadioGroup radioGroup;
    private Button btnCalc;
    String textoDoRadio;
    double salario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        salarioInput = findViewById(R.id.salarioInput);
        radioGroup = findViewById(R.id.radioGroup);
        btnCalc = findViewById(R.id.btnCalc);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                textoDoRadio = radioButton.getText().toString();

                salario = Double.parseDouble(salarioInput.getText().toString());
            }
        });
    }

    public void goToScreenCalc(View view) {
        int idButton = radioGroup.getCheckedRadioButtonId();

        if (salarioInput.getText().toString().equals("")) {
            Toast.makeText(Tela2.this, "Por favor, informe seu salário!", Toast.LENGTH_SHORT).show();
        } else if (idButton == -1) {
            Toast.makeText(Tela2.this, "Por favor, selecione MENSAL ou ANUAL!", Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            Intent intent = new Intent(Tela2.this, Tela3.class);

            bundle.putDouble("salario", salario);
            bundle.putString("anualOuMensal", textoDoRadio);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }
}