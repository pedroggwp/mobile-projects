package com.example.authenticationfirebase;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CadastroActivity extends AppCompatActivity {

    private String txtName, txtEmail, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button button = findViewById(R.id.btCadastro);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validar se os campos foram preenchidos
                // se sim, cadastrar usuário
                txtName = ((EditText) findViewById(R.id.editNome)).getText().toString();
                txtEmail = ((EditText) findViewById(R.id.editEmail)).getText().toString();
                txtPassword = ((EditText) findViewById(R.id.editSenha)).getText().toString();

                saveLogin();
            }
        });
    }

    private void saveLogin() {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        Log.d("EmailSenha", txtEmail +" " + txtPassword);

        auth.createUserWithEmailAndPassword(txtEmail, txtPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "Logado com sucesso!", Toast.LENGTH_SHORT).show();

                            // Update username and photo
                            FirebaseUser userLogin = auth.getCurrentUser();
                            UserProfileChangeRequest userProfile = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(txtName)
                                    .setPhotoUri(Uri.parse("https://jornalibia.com.br/wp-content/uploads/2019/05/Minions.jpg"))
                                    .build();

                            userLogin.updateProfile(userProfile)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                finish();
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(CadastroActivity.this, "Deu RED!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}