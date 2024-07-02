package com.example.mobile_pedrohenrique.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mobile_pedrohenrique.R;
import com.google.android.material.textfield.TextInputEditText;

public class CollaboratorForm extends AppCompatActivity {

    private TextInputEditText text;
    private Button save;
    private Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborator_form);

        text = findViewById(R.id.form_num_cracha);
        save = findViewById(R.id.bt_save);
        cancel = findViewById(R.id.bt_cancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.getText() != null && !text.getText().toString().isEmpty()) {
                    TextInputEditText crachaNum = findViewById(R.id.form_num_cracha);

//                    Collaborator collaborator = new Collaborator(Integer.parseInt(crachaNum.getText().toString()), new Date(), null);

                    Intent intent = new Intent();
//                    intent.putExtra("collaborator", collaborator);

                    setResult(RESULT_OK, intent);

                    finish();
                } else {
                    Toast.makeText(CollaboratorForm.this, "Insira um número ou cancele", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
}
