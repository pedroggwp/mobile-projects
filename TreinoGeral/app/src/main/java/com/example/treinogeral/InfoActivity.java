package com.example.treinogeral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class InfoActivity extends AppCompatActivity {

    private TextInputEditText nameEditText;
    private TextInputEditText ageEditText;
    private Button btnSendInfo;
    private boolean canExit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        nameEditText = findViewById(R.id.nameEditText);
        ageEditText = findViewById(R.id.ageEditText);
        btnSendInfo = findViewById(R.id.btnSendInfo);

        btnSendInfo.setOnClickListener(v -> {
            String name = nameEditText.getText().toString();
            String age = ageEditText.getText().toString();

            if (verifyContent(name, age)) {
                Bundle bundle = new Bundle();
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);

                bundle.putString("name", name);
                bundle.putInt("age", Integer.parseInt(age));
                intent.putExtras(bundle);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(InfoActivity.this, "Por favor, preencha todas as informações necessárias.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (canExit) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Por favor, preencha todas as informações e envie-as.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean verifyContent(String name, String age) {
        boolean isContentValid = !name.equals("") && !age.equals("");

        if (isContentValid) {
            canExit = true;
        }
        return isContentValid;
    }
}