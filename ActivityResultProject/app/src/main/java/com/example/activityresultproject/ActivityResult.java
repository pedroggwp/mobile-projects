package com.example.activityresultproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ActivityResult extends AppCompatActivity {
    EditText text;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        text = findViewById(R.id.edtText);
        btn = findViewById(R.id.btnBack);

        btn.setOnClickListener(view -> {
            Intent intent = new Intent(ActivityResult.this, MainActivity.class);
            intent.putExtra("return", text.getText().toString());

            // voltar
            setResult(RESULT_OK, intent);
            finish();

        });
    }
}