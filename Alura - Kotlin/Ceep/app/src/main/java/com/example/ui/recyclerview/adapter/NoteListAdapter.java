package com.example.ui.recyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.model.Note;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    private final List<Note> allNotes;
    private final Context context;

    public NoteListAdapter(Context context, List<Note> allNotes) {
        this.context = context;
        this.allNotes = allNotes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // View para representar cada nota
        View createdView = LayoutInflater.from(context).inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(createdView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = allNotes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return allNotes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_nota_titulo);
            description = itemView.findViewById(R.id.item_nota_descricao);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,  "View Holder clicado", Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(Note note) {
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }
    }

    public void add(Note note) {
        allNotes.add(note);
        notifyDataSetChanged();
    }
}
