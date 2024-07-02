//package com.example.recyclerview;
//
//import android.content.Context;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.ItemTouchHelper;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.Collections;
//
//public class NoteItemTouchCallback extends ItemTouchHelper.Callback {
//
//    private AdapterNote adapterNote;
//    ConnectionDB db = new ConnectionDB();
//
//    public NoteItemTouchCallback(AdapterNote adapterNote) {
//        this.adapterNote = adapterNote;
//    }
//
//    @Override
//    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        int swipeMovement = ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
//
//        int dragMovement = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT;
//
//        return makeMovementFlags(swipeMovement, dragMovement);
//    }
//
//    // segura e arrasta -> equivalent a dragMovement acima
//    @Override
//    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//
//        int viewHolderPosition = viewHolder.getAdapterPosition();
//        int targetPosition = target.getAdapterPosition();
//
//        adapterNote.changePosition(viewHolderPosition, targetPosition);
//        return true;
//    }
//
//    @Override
//    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//        int swipedPosition = viewHolder.getAdapterPosition() + 1; // nao vai dar certo no firebase provavelmente
//
//        System.out.println("Swiped Position " + swipedPosition);
//
//        db.removeFromFirebase(swipedPosition);
//    }
//}
