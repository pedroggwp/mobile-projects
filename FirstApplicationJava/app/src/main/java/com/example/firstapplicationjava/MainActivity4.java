package com.example.firstapplicationjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void convertNumber(View view) {
        EditText inputNumber = findViewById(R.id.inputNumber);
        TextView resultConverter = findViewById(R.id.resultConverter);

        double valorDolar = Double.parseDouble(inputNumber.getText().toString());
        Double valorConvertido = valorDolar * 4.95;

        DecimalFormat df = new DecimalFormat("#.00");
        String valorFormatado = df.format(valorConvertido);

        resultConverter.setText("R$ " + valorFormatado);
    }
}