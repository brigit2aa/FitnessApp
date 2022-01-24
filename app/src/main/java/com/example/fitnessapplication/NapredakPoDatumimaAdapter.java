package com.example.fitnessapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NapredakPoDatumimaAdapter extends RecyclerView.Adapter<NapredakPoDatumimaAdapter.MyViewHolderNapredakPoDatumima>{

    Context context;
    ArrayList<Napredak> napredakArrayList;

    public NapredakPoDatumimaAdapter(Context context, ArrayList<Napredak> napredakArrayList) {
        this.context = context;
        this.napredakArrayList = napredakArrayList;
    }

    @NonNull
    @Override
    public MyViewHolderNapredakPoDatumima onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_napredakpodatumima, parent, false);
        return new MyViewHolderNapredakPoDatumima(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNapredakPoDatumima holder, int position) {
        Napredak napredak = napredakArrayList.get(position);
        holder.datum.setText(napredak.getDatum());
        holder.trenutnaTezina.setText(napredak.getTrenutnaTezina());
        holder.bmi.setText(napredak.getBmi());

    }

    @Override
    public int getItemCount() {
        return napredakArrayList.size();
    }

    public static class MyViewHolderNapredakPoDatumima extends RecyclerView.ViewHolder{

        TextView datum, trenutnaTezina, bmi;

        public MyViewHolderNapredakPoDatumima(@NonNull View itemView) {
            super(itemView);

            datum = itemView.findViewById(R.id.datum);
            trenutnaTezina = itemView.findViewById(R.id.trenutnaTezina);
            bmi = itemView.findViewById(R.id.bmi);
        }
    }
}
