package com.example.authenticationfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView foto = findViewById(R.id.imgFoto);
        String url = FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString();
        Glide.
                with(this).
                load(url).
                into(foto);

        TextView email = findViewById(R.id.txtEmail);
        email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        TextView nome = findViewById(R.id.txtNome);
        nome.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

        TextView logout = findViewById(R.id.txtLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.
                        getInstance
                                ().signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}