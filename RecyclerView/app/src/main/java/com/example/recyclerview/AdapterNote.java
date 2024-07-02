package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AdapterNote extends RecyclerView.Adapter<AdapterNote.NoteViewHolder> {

    private List<Note> noteList;
    public AdapterNote(List<Note> noteList) {
        this.noteList = noteList;
    }
    ConnectionDB db = new ConnectionDB();
    private final AdapterNote adapterNote = new AdapterNote(noteList);

    @NonNull
    @Override
    public AdapterNote.NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // carregar o template de visualização
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nota, parent, false);

        // chamar o ViewHolder
        return new NoteViewHolder(itemView);
    }

    // OLHANDO PARA O ITEM ESPECÍFICO
    @Override
    public void onBindViewHolder(@NonNull AdapterNote.NoteViewHolder holder, int position) {
        holder.title.setText(noteList.get(position).getTitle());
        holder.description.setText(noteList.get(position).getDescription());

        // se necessário fazer algo personalizado
        if (position % 2 != 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.green));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }

        holder.bind(noteList.get(position));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                db.removeFromFirebase(v.getId());
                db.getNotesInFirebase(noteList, adapterNote);
                return false;
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int clickedPosition = holder.getAdapterPosition();
//
//                if (clickedPosition != RecyclerView.NO_POSITION) {
//                    noteList.remove(clickedPosition);
//                    notifyItemRemoved(clickedPosition);
//                }
//            }
//        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "position: " + holder.title.getText(), Toast.LENGTH_SHORT). show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    // vinculando o xml com o objeto Java
    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        Note bindedNote;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);
        }

        public void bind(Note note) {
            bindedNote = note;
        }

    }

    public void changePosition(int initialPosition, int finalPosition) {
        Collections.swap(noteList, initialPosition, finalPosition);
        notifyItemMoved(initialPosition, finalPosition);
    }

//    public void removeItem(int position) {
//        noteList.remove(position);
//        notifyItemRemoved(position);
//    }
}
