package com.example.retrofitapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button getPhotoActivity;
    private Button getPhotoById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPhotoActivity = findViewById(R.id.getPhotos);
        getPhotoById = findViewById(R.id.getId);

        getPhotoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_getPhoto.class);
                startActivity(intent);
            }
        });

        getPhotoById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity_getPhoto.class).putExtra("byId", true);
                startActivity(intent);
            }
        });
    }
}