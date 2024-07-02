package com.aula.buffetalegria;


import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.textfield.TextInputEditText;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdapterPessoa extends RecyclerView.Adapter<AdapterPessoa.MeuViewHolder> {

    private final List<Pessoa> listaPessoa;

    public AdapterPessoa(List<Pessoa> listaPessoa) {
        this.listaPessoa = listaPessoa;
    }

    @NonNull
    @Override
    public AdapterPessoa.MeuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // carregar o Template de visualização
        View viewItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pessoa,parent,false);

        // chamar o MeuViewHolder para carregar os dados
        return new MeuViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPessoa.MeuViewHolder holder, int position) {

        // Converter Date para LocalDate
        Instant instant = listaPessoa.get(position).getDtNasc().toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate dataDeNascimento = instant.atZone(zoneId).toLocalDate();
        LocalDate dataAtual = LocalDate.now();

        // Calcula a idade
        Period periodo = Period.between(dataDeNascimento, dataAtual);

        //carregar os dados nos objs
        holder.itNome.setText(listaPessoa.get(position).getNome());
        holder.itIdade.setText(periodo.getYears()+"");
        holder.itRank.setText(listaPessoa.get(position).getRank() + "");

        //personalizar restrição
        if (listaPessoa.get(position).isRestricoes()) {
            holder.itNome.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.red));
        } else {
            holder.itNome.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.black));
        }

        //Click de rank
        holder.itRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertName = new AlertDialog.Builder(view.getContext());

                TextInputEditText txtNome = new TextInputEditText(view.getContext());
                txtNome.setEnabled(false);
                txtNome.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.pink_80));
                txtNome.setText(holder.itNome.getText());
                txtNome.setTextSize(24);

                RatingBar  ratingBar = new RatingBar(view.getContext());
                ratingBar.setNumStars(5);
                ratingBar.setMax(5);
                ratingBar.setRating(listaPessoa.get(position).getRank());
                ratingBar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));


                LinearLayout layoutName = new LinearLayout(view.getContext());
                layoutName.setGravity(Gravity.CENTER);
                layoutName.setOrientation(LinearLayout.VERTICAL);
                layoutName.addView(txtNome);
                layoutName.addView(ratingBar);

                alertName.setView(layoutName);

                ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        holder.itRank.setText(String.valueOf(rating));

                        //atualizar a lista
                        listaPessoa.get(position).setRank(rating);
                    }
                });

                alertName.setPositiveButton("[fechar]", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                });
                alertName.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPessoa.size();
    }

    public class MeuViewHolder extends RecyclerView.ViewHolder {

        ImageView itFoto;
        Chip itRank;
        TextView itNome;
        TextView itIdade;

        public MeuViewHolder(@NonNull View itemView) {
            super(itemView);
            itFoto = itemView.findViewById(R.id.formulario_foto);
            itRank = itemView.findViewById(R.id.itRank);
            itNome = itemView.findViewById(R.id.itNome);
            itIdade = itemView.findViewById(R.id.itIdade);
        }
    }
}
