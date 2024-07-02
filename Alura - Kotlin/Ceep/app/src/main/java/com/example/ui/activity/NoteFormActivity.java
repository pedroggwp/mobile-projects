package com.example.ui.activity;

import static com.example.ui.activity.NoteConstants.KEY_NOTE;
import static com.example.ui.activity.NoteConstants.RESULT_CODE_CREATED_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.model.Note;

public class NoteFormActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_form_save, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (isSaveMenu(item)) {
            Note createdNote = createNote();

            returnNote(createdNote);

            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnNote(Note createdNote) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_NOTE, createdNote);

        setResult(RESULT_CODE_CREATED_NOTE, resultIntent);
    }

    @NonNull
    private Note createNote() {
        EditText title = findViewById(R.id.formulario_nota_titulo);
        EditText description = findViewById(R.id.formulario_nota_descricao);
        return new Note(title.getText().toString(), description.getText().toString());
    }

    private static boolean isSaveMenu(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_form_note_ic_save;
    }
}