package com.example.ui.activity;

import static com.example.ui.activity.NoteConstants.INVALID_POSITION;
import static com.example.ui.activity.NoteConstants.KEY_NOTE;
import static com.example.ui.activity.NoteConstants.KEY_POSITION;
import static com.example.ui.activity.NoteConstants.RESULT_CODE_CREATED_NOTE;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.R;
import com.example.model.Note;

public class NoteFormActivity extends AppCompatActivity {

    private int receivedPosition = INVALID_POSITION;
    private TextView title;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_form);

        findFields();

        Intent receivedData = getIntent();

        // verifica se temos acesso à nota, logo, podemos pegar a nota que foi recebida
        if (receivedData.hasExtra(KEY_NOTE)) {
            Note receivedNote = (Note) receivedData.getSerializableExtra(KEY_NOTE);

            receivedPosition = receivedData.getIntExtra(KEY_POSITION, INVALID_POSITION);

            title.setText(receivedNote.getTitle());
            description.setText(receivedNote.getDescription());
        }
    }

    public void findFields() {
        title = findViewById(R.id.formulario_nota_titulo);
        description = findViewById(R.id.formulario_nota_descricao);
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
        resultIntent.putExtra(KEY_POSITION, receivedPosition);
        setResult(RESULT_CODE_CREATED_NOTE, resultIntent);
    }

    @NonNull
    private Note createNote() {
        return new Note(title.getText().toString(), description.getText().toString());
    }

    private static boolean isSaveMenu(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_form_note_ic_save;
    }
}