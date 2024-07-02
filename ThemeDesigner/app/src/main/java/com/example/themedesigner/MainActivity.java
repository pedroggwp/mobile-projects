package com.example.themedesigner;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void boxMsg(View view) {
        // usando dialog
        Dialog boxMsg = new Dialog(MainActivity.this);
        boxMsg.setContentView(R.layout.custom);
        boxMsg.getWindow().setLayout(WRAP_CONTENT, WRAP_CONTENT);
        boxMsg.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_background));

        // nao permitir clique fora da box
        boxMsg.setCancelable(false);

        // inicializar os componentes da boxMsg
        TextView txtTitle = boxMsg.findViewById(R.id.textTitle);
        txtTitle.setText("ANDROID");

        TextView txtMessage = boxMsg.findViewById(R.id.textMessage);
        txtMessage.setText("Você está avançando na programação");

        // obter o comportamento dos botoões
        Button btnOk = boxMsg.findViewById(R.id.btnOk);
        Button btnCancel = boxMsg.findViewById(R.id.btnCancel);

        btnOk.setText("SIM");
        btnCancel.setText("NÃO");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Você disse SIM", Toast.LENGTH_SHORT).show();
                boxMsg.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Você disse NÃO", Toast.LENGTH_SHORT).show();
                boxMsg.dismiss();
            }
        });

        boxMsg.show();
    }
}