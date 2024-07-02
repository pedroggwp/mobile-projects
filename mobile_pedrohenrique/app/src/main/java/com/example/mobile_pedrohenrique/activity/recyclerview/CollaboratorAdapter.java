package com.example.mobile_pedrohenrique.activity.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_pedrohenrique.R;
import com.example.mobile_pedrohenrique.activity.model.Collaborator;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CollaboratorAdapter extends RecyclerView.Adapter<CollaboratorAdapter.collaboratorViewHolder> {

    private List<Collaborator> collaboratorList = new ArrayList<>();
    private Context context;

    public CollaboratorAdapter(Context context, List<Collaborator> collaboratorList) {
        this.context = context;
        this.collaboratorList = collaboratorList;
    }

    @NonNull
    @Override
    public collaboratorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(context).inflate(R.layout.collaborator_item,parent,false);
        return new collaboratorViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull collaboratorViewHolder holder, int position) {
        Collaborator collaborator = collaboratorList.get(position);
        holder.bind(collaborator);
    }

    @Override
    public int getItemCount() {
        return collaboratorList.size();
    }

    public void add(Collaborator collaborator) {
        collaboratorList.add(collaborator);
        notifyDataSetChanged();
    }

    public static class collaboratorViewHolder extends RecyclerView.ViewHolder {

        private final TextView itCracha;
        private final TextView itStartDate;
        private final TextView itEndDate;
        private final Button btEnd;


        public collaboratorViewHolder(@NonNull View itemView) {
            super(itemView);
            itCracha = itemView.findViewById(R.id.collaboratorCracha);

            itStartDate = itemView.findViewById(R.id.startDate);
            itEndDate = itemView.findViewById(R.id.endDate);

            btEnd = itemView.findViewById(R.id.bt_end);
        }

        public void bind(Collaborator collaborator) {
            itCracha.setText(collaborator.getCracha());

            SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
            itStartDate.setText(dtf.format(collaborator.getStartService()));

            if (collaborator.getEndService() != null) {
                itEndDate.setText(dtf.format(collaborator.getEndService()));
                btEnd.setVisibility(View.GONE);
            } else {
                btEnd.setVisibility(View.VISIBLE);
                itEndDate.setVisibility(View.GONE);

                btEnd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // fazer update
                    }
                });
            }
        }
    }
}
