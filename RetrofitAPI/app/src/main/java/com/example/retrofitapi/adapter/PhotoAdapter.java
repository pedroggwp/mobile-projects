package com.example.retrofitapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitapi.R;
import com.example.retrofitapi.model.Photo;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>{

    List<Photo> photos;

    public PhotoAdapter(List<Photo> photos) {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoAdapter.PhotoViewHolder holder, int position) {
        holder.albumView.setText(photos.get(position).getAlbumId());
        holder.tituloView.setText(photos.get(position).getTitle());

        String url = photos.get(position).getThumbnailUrl();
        Glide.with(holder.fotoView.getContext()).asBitmap().load(url).into(holder.fotoView);
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        TextView albumView;
        TextView tituloView;
        ImageView fotoView;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);

            albumView = itemView.findViewById(R.id.album);
            tituloView = itemView.findViewById(R.id.titulo);
            fotoView = itemView.findViewById(R.id.foto);
        }
    }
}
