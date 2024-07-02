package com.example.dao;

import com.example.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NoteDAO {

    private final static ArrayList<Note> notes = new ArrayList<>();

    public List<Note> allNotes() {
        return (List<Note>) notes.clone();
    }

    public void insertNote(Note... notes) {
        NoteDAO.notes.addAll(Arrays.asList(notes));
    }

    public void updateNote(int position, Note note) {
        notes.set(position, note);
    }

    public void deleteNote(int position) {
        notes.remove(position);
    }

    public void changePositionNote(int initialPosition, int finalPosition) {
        Collections.swap(notes, initialPosition, finalPosition);
    }

    public void deleteAll() {
        notes.clear();
    }
}
