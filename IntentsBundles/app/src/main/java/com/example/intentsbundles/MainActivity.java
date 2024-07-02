package com.example.intentsbundles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name, phone, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.name = findViewById(R.id.name);
        this.phone = findViewById(R.id.phone);
        this.email = findViewById(R.id.email);
    }

    public void informationButton(View view) {
        Toast.makeText(MainActivity.this,
                        "Name: "+ name.getText() +
                                "\nPhone: "+ phone.getText() +
                                "\nEmail: "+ email.getText(),
                Toast.LENGTH_SHORT)
                .show();
    }

    public void infoApp(View view) {
        Bundle envelopData = new Bundle();
        envelopData.putString("name", name.getText().toString());
        envelopData.putInt("phone", Integer.parseInt(phone.getText().toString()));
        envelopData.putString("email", email.getText().toString());

        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtras(envelopData);
        startActivity(intent);
        finish();
    }

    public void diskApp(View view) {
//        EditText phone = (EditText) findViewById(R.id.phone);
        Intent intent = new Intent(Intent.ACTION_DIAL);

        intent.setData(Uri.parse("tel:" + phone.getText().toString()));
        startActivity(intent);
    }

    public void sendEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {email.getText().toString()});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Suporte App: ");
        intent.putExtra(Intent.EXTRA_TEXT, "Olá " + name.getText().toString());
        startActivity(intent);
    }
}