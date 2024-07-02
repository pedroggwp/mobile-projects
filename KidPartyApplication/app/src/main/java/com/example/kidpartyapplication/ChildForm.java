package com.example.kidpartyapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.util.Objects;

public class ChildForm extends AppCompatActivity {

    private static final int REQUEST_PERMISSIONS = 100;
//    private TextView dateTextView;
//    private RecyclerView rv;
    private TextInputEditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_form);
        name = findViewById(R.id.childNameForm);

//        dateTextView = findViewById(R.id.dateTextView);

//        dateTextView.setOnClickListener(v -> showDatePickerDialog());

        checkPermissions();
    }

//    private void showDatePickerDialog() {
//        final Calendar c = Calendar.getInstance();
//        int year = c.get(Calendar.YEAR);
//        int month = c.get(Calendar.MONTH);
//        int day = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                (view, selectedYear, selectedMonth, selectedDay) -> {
//                    String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
//                    dateTextView.setText(selectedDate);
//                }, year, month, day);
//        datePickerDialog.show();
//    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_PERMISSIONS);
        }
    }

    public void saveChild(View view) {
        String childName = Objects.requireNonNull(name.getText()).toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("childName", childName);
        setResult(RESULT_OK, resultIntent);

        Toast.makeText(ChildForm.this, "Pessoa salva!", Toast.LENGTH_SHORT).show();

        finish();


//        String responsableText = responsable.getText().toString();
//        long phoneNumberText = Long.parseLong(phoneNumber.getText().toString());

//        TextView ageTextView = findViewById(R.id.childAge);

//        String dateText = ageTextView.getText().toString();

        // Define the date format expected from the TextView
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//        Date date = new Date();
//
//        try {
//            // Parse the date text into a Date object
//            date = dateFormat.parse(dateText);
//            // Now 'date' contains the parsed date value
//        } catch (ParseException e) {
//            // Handle parsing exceptions
//            e.printStackTrace();
//        }
//
//
//        boolean isRestrictedText = Boolean.parseBoolean(isRestricted.getText().toString());
//        int rankText = Integer.parseInt(rank.getText().toString());

    }
}