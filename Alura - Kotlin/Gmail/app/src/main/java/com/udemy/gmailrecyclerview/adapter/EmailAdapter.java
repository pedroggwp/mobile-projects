package com.udemy.gmailrecyclerview.adapter;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udemy.gmailrecyclerview.R;
import com.udemy.gmailrecyclerview.model.Email;

import java.util.List;

public class EmailAdapter extends RecyclerView.Adapter<EmailAdapter.EmailViewHolder> {

    private EmailAdapterListener listener;
    private final List<Email> emails;
    public final SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int currentSelectedPos;

    public EmailAdapter(List<Email> emails) {
        this.emails = emails;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setListener(EmailAdapterListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.email_item,
                parent,
                false
        );

        return new EmailViewHolder(cardView); // passando isso, conseguiremos pegar todos elementos do CardView a partir da classe EmailViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull EmailViewHolder holder, int position) {
        Email email = emails.get(position); // pegando item atual da lista
        holder.bind(email);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedItems.size() > 0 && listener != null) {
                    listener.onItemClick(holder.getAdapterPosition()); // se der erro, trocar por position
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null) {
                    listener.onItemLongClick(holder.getAdapterPosition());
                }
                return true;
            }
        });

        if (currentSelectedPos == position) currentSelectedPos = -1;
    }

    @Override
    public int getItemCount() {
        return emails.size();
    }

    public void deleteEmails() {
        Log.i("Teste", "delete email");
    }

    public void toggleSelection(int position) {
        currentSelectedPos = position;

        if (selectedItems.get(position)) {
            selectedItems.delete(position);
            emails.get(position).setSelected(false);
        } else {
            selectedItems.put(position, true);
            emails.get(position).setSelected(true);
        }
        notifyItemChanged(position);
    }

    // ViewHolder para gerenciar as views (email_item.xml)
    static class EmailViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtIcon;
        private final TextView txtUser;
        private final TextView txtSubject;
        private final TextView txtPreview;
        private final TextView txtDate;
        private final ImageView imgStar;

        public EmailViewHolder(@NonNull View itemView) {
            super(itemView);

            txtIcon = itemView.findViewById(R.id.txt_icon);
            txtUser = itemView.findViewById(R.id.txt_user);
            txtSubject = itemView.findViewById(R.id.txt_subject);
            txtPreview = itemView.findViewById(R.id.txt_preview);
            txtDate = itemView.findViewById(R.id.txt_date);
            imgStar = itemView.findViewById(R.id.img_star);
        }

        public void bind(Email email) {
            int hash = email.getUser().hashCode();

            txtIcon.setText(String.valueOf(email.getUser().charAt(0)));
            txtIcon.setBackground(oval(Color.rgb(hash, hash / 2, 0), txtIcon));
            txtUser.setText(email.getUser());
            txtSubject.setText(email.getSubject());
            txtPreview.setText(email.getSubject());
            txtDate.setText(email.getDate());

            txtUser.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);
            txtSubject.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);
            txtDate.setTypeface(Typeface.DEFAULT, email.isUnread() ? Typeface.BOLD : Typeface.NORMAL);

            imgStar.setImageResource(email.isStared() ? R.drawable.android_star_2 : R.drawable.android_star_outline);
        }
    }

    private static ShapeDrawable oval(@ColorInt int color, View view) {
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.setIntrinsicHeight(view.getHeight());
        shapeDrawable.setIntrinsicWidth(view.getWidth());
        shapeDrawable.getPaint().setColor(color);

        return shapeDrawable;
    }

    public interface EmailAdapterListener {
        void onItemClick(int position);
        void onItemLongClick(int position);
    }
}
