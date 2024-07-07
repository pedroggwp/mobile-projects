package com.udemy.gmailrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.udemy.gmailrecyclerview.adapter.EmailAdapter;
import com.udemy.gmailrecyclerview.model.Emails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EmailAdapter emailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAdapter = new EmailAdapter(new ArrayList<>(Emails.fakeEmails()));

        RecyclerView rv = findViewById(R.id.recycler_view_main);

        rv.setAdapter(emailAdapter);
    }
}