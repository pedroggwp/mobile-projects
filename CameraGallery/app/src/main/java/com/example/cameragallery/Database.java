package com.example.cameragallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class Database {

    public void uploadPhoto(Context context, ImageView photoImageView, Map<String, String> documentData) {

        // Conversão de imagem para Bitmap
        Bitmap bitmap = ((BitmapDrawable) photoImageView.getDrawable()).getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte[] dataBytes = byteArrayOutputStream.toByteArray();

        // Abrir database
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storage.getReference("gallery").child("photo_" + System.currentTimeMillis() + ".jpg")
                .putBytes(dataBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {

                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(context, "Upload feito com sucesso!", Toast.LENGTH_SHORT).show();
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                            @Override
                            public void onSuccess(Uri uri) {
                                documentData.put("url", uri.toString());
                                Toast.makeText(context, uri.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("RUIM", e.getMessage());
                    }
                });
    }

    public void downloadPhoto(ImageView viewImg, Uri urlFirebase) {
        viewImg.setRotation(0);
        Glide.with(viewImg.getContext()).asBitmap().load(urlFirebase).into(viewImg);
    }
}
