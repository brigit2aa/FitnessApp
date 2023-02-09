package com.example.fitnessapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReceptAdapter extends RecyclerView.Adapter<ReceptAdapter.MyViewHolderRecept>{

    Context context;
    ArrayList<Recept> receptArrayList;

    public ReceptAdapter(Context context, ArrayList<Recept> receptArrayList) {
        this.context = context;
        this.receptArrayList = receptArrayList;
    }

    @NonNull
    @Override
    public MyViewHolderRecept onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recept, parent, false);
        return new MyViewHolderRecept(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderRecept holder, int position) {

        Recept recept = receptArrayList.get(position);
        holder.brojKalorija.setText(recept.getBrojKalorija());

        holder.layoutrucak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogprocitajrecept);
                TextView dorucak = dialog.findViewById(R.id.dorucak);
                TextView prvaUzina = dialog.findViewById(R.id.prvaUzina);
                TextView rucak = dialog.findViewById(R.id.rucak);
                TextView drugaUzina = dialog.findViewById(R.id.drugaUzina);
                TextView vecera = dialog.findViewById(R.id.vecera);
                Button btnZatvori = dialog.findViewById(R.id.zatvori);


                dorucak.setText(recept.getDorucak());
                prvaUzina.setText(recept.getPrvaUzina());
                rucak.setText(recept.getRucak());
                drugaUzina.setText(recept.getDrugaUzina());
                vecera.setText(recept.getVecera());

                btnZatvori.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return receptArrayList.size();
    }

    public static class MyViewHolderRecept extends RecyclerView.ViewHolder{

        TextView brojKalorija;
        LinearLayout layoutrucak;

        public MyViewHolderRecept(@NonNull View itemView) {
            super(itemView);

            brojKalorija = itemView.findViewById(R.id.brojKalorija);
            layoutrucak = itemView.findViewById(R.id.receptBrKalorija);

        }
    }
}
