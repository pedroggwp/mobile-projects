package com.example.kidpartyapplication;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Date;
import java.util.List;

public class AdapterChild extends RecyclerView.Adapter<AdapterChild.ChildViewHolder> {

    private List<Child> childList;

    public void setChildList(List<Child> childList) {
        this.childList = childList;
    }

    @NonNull
    @Override
    public ChildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View childView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_model, parent, false);

        return new ChildViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildViewHolder holder, int position) {
        holder.itemView.findViewById(R.id.childName);
    }

    @Override
    public int getItemCount() {
        return childList.size();
    }

    public static class ChildViewHolder extends RecyclerView.ViewHolder {



        public ChildViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void save(Child child) {
        childList.add(child);
        notifyDataSetChanged();
    }
}
