package com.example.praticamobilegrupo;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tela4_2 extends AppCompatActivity {
    Button btDownload;
    Button btInformacoes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela42);
        btDownload = findViewById(R.id.download);
        btInformacoes = findViewById(R.id.informacoes);
        btDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle url = new Bundle();
                url.putString("url", "https://www.gov.br/receitafederal/pt-br");
                Intent intent = new Intent(Tela4_2.this,Tela5.class);
                intent.putExtras(url);
                startActivity(intent);
            }
        });
        btInformacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle url = new Bundle();
                url.putString("url", "https://www.gov.br/receitafederal/pt-br/centrais-de-conteudo/download/pgd/dirpf");
                Intent intent = new Intent(Tela4_2.this,Tela5.class);
                intent.putExtras(url);
                startActivity(intent);
            }
        });
    }
}