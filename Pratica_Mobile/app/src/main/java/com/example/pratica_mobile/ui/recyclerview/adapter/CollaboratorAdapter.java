package com.example.pratica_mobile.ui.recyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pratica_mobile.R;

public class CollaboratorAdapter extends RecyclerView.Adapter<CollaboratorAdapter.PicPayViewHolder> {
    @NonNull
    @Override
    public CollaboratorAdapter.PicPayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.collaborator_item,parent,false);
        return new PicPayViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull CollaboratorAdapter.PicPayViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class PicPayViewHolder extends RecyclerView.ViewHolder {
        public PicPayViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
