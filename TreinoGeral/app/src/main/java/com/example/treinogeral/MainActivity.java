package com.example.treinogeral;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button buttonInfo, buttonInfoDialog;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInfo = findViewById(R.id.btnInfo);
        buttonInfoDialog = findViewById(R.id.btnSendInfoDialogMain);

        buttonInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });

        buttonInfoDialog.setOnClickListener(v -> {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.activity_dialog);
            Objects.requireNonNull(dialog.getWindow()).setLayout(WRAP_CONTENT, WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.custom_background));

            Button sendInfo = dialog.findViewById(R.id.btnSendInfoDialog);
            TextInputEditText name = dialog.findViewById(R.id.nameDialog);
            TextInputEditText age = dialog.findViewById(R.id.ageDialog);

            String nameStr = name.getText().toString();
            String ageStr = age.getText().toString();

            sendInfo.setOnClickListener(v1 -> {
                if (verifyContent(nameStr, ageStr)) {
                    textViewResult.setText("Name user: " + nameStr + "\nAge user: " + ageStr);
                } else {
                    Toast.makeText(MainActivity.this, "Por favor, preencha todas as informações necessárias.", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();
        });

        textViewResult = findViewById(R.id.textViewResult);

        Bundle bundle = getIntent().getExtras();


        if (bundle != null) {
            String name = bundle.getString("name");
            int age = bundle.getInt("age");
            textViewResult.setText("Name user: " + name + "\nAge user: " + age);
        }
    }

    public boolean verifyContent(String name, String age) {
        Log.d("resultado", name + " " + age);
        return !name.equals("") && !age.equals("");
    }
}