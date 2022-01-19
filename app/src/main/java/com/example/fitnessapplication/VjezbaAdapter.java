package com.example.fitnessapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VjezbaAdapter extends RecyclerView.Adapter<VjezbaAdapter.MyViewHolderVjezba>{

    Context context;
    ArrayList<Vjezba> vjezbaArrayList;

    public VjezbaAdapter(Context context, ArrayList<Vjezba> vjezbaArrayList) {
        this.context = context;
        this.vjezbaArrayList = vjezbaArrayList;
    }

    @NonNull
    @Override
    public MyViewHolderVjezba onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vjezba, parent, false);
        return  new MyViewHolderVjezba(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderVjezba holder, int position) {

        Vjezba vjezba = vjezbaArrayList.get(position);
        holder.trosiKalorije.setText(vjezba.getKalorije());
        holder.imeVjezbe.setText(vjezba.getImeVjezbe());
        holder.opis.setText(vjezba.getOpis());
    }

    @Override
    public int getItemCount() {
        return vjezbaArrayList.size();
    }

    public static class MyViewHolderVjezba extends RecyclerView.ViewHolder{

        TextView trosiKalorije, imeVjezbe, opis;

        public MyViewHolderVjezba(@NonNull View itemView) {
            super(itemView);

            trosiKalorije = itemView.findViewById(R.id.trosenjeKalorija);
            imeVjezbe = itemView.findViewById(R.id.imeVjezbe);
            opis = itemView.findViewById(R.id.opis);
        }
    }
}
