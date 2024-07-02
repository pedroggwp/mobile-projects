package com.example.pratica_mobile.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.pratica_mobile.R;
import com.example.pratica_mobile.db.Database;

import java.text.SimpleDateFormat;

public class CollaboratorForm extends AppCompatActivity {

    private Database dbPicPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collaborator_form);

        SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");


    }
}