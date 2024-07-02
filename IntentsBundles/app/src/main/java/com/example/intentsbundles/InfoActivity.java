package com.example.intentsbundles;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InfoActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle data = getIntent().getExtras();

        String name = data.getString("name");
        int phoneNumber = data.getInt("phone");
        String email = data.getString("email");

        EditText result = (EditText) findViewById(R.id.resultData);

        result.setText(data +
                "\n\n Separando os dados \n\n" +
                "Nome: " + name + "\n" +
                "Phone: " + phoneNumber + "\n" +
                "Email:" + email);
    }

    public void backHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }


    
}