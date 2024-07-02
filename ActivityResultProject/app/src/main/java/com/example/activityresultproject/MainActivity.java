package com.example.activityresultproject;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    Button btnResult;
    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        btnResult = findViewById(R.id.btnResult);

        // definindo o activity result
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                txtResult.setText(data.getStringExtra("return"));
            } else {
                Toast.makeText(this, "Você usou o VOLTAR!!", Toast.LENGTH_LONG).show();
            }
        });

        // Onclick botão
        btnResult.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ActivityResult.class);

            // abrir uma ActivityResult
            launcher.launch(intent);
        });
    }


}