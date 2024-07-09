package com.udemy.gmailrecyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.udemy.gmailrecyclerview.adapter.EmailAdapter;
import com.udemy.gmailrecyclerview.model.Email;
import com.udemy.gmailrecyclerview.model.Emails;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private EmailAdapter emailAdapter;
    private ActionMode actionMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailAdapter = new EmailAdapter(new ArrayList<>(Emails.fakeEmails()));

        final RecyclerView rv = findViewById(R.id.recycler_view_main);
        rv.setAdapter(emailAdapter);

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEmail();
                rv.scrollToPosition(0);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHandler(
                        0,
                        ItemTouchHelper.RIGHT
                )
        );

        itemTouchHelper.attachToRecyclerView(rv);

        emailAdapter.setListener(new EmailAdapter.EmailAdapterListener() {
            @Override
            public void onItemClick(int position) {
                enableActionMode(position);
            }

            @Override
            public void onItemLongClick(int position) {
                enableActionMode(position);
            }
        });
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(new ActionMode.Callback() {

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.action_delete) {
                        emailAdapter.deleteEmails();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });

            emailAdapter.toggleSelection(position);

            final int size = emailAdapter.selectedItems.size();

            if (size == 0) {
                actionMode.finish();
            } else {
                actionMode.setTitle(size + "");
                actionMode.invalidate();
            }
        }
    }

    private void addEmail() {

        Date currentDate = new Date();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdt = new SimpleDateFormat("d MMM");
        String currentDateTime = sdt.format(currentDate);

        // para simular novos emails, precisamos pegar o adapter e os emails já disponíveis
        emailAdapter.getEmails().add(
                0,
                Email.EmailBuilder.builder()
                        .setUser("Meta")
                        .setSubject("Meta sent you an new oppotunity")
                        .setPreview("Jeff Bazzos is now a lier")
                        .setDate(currentDateTime)
                        .setStared(false)
                        .setUnread(true)
                        .build()

        );

        emailAdapter.notifyItemInserted(0);
    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {

        public ItemTouchHandler(int dragDirs, int swipeDirs) {
            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int from = viewHolder.getAdapterPosition(); // posição atual do movimento
            int to = target.getAdapterPosition(); // posição que movemos o item

            Collections.swap(emailAdapter.getEmails(), from, to); // reordenando a lista
            emailAdapter.notifyItemMoved(from, to); // trocamos apenas a posição do viewholder

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            emailAdapter.getEmails().remove(viewHolder.getAdapterPosition());
            emailAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }
}