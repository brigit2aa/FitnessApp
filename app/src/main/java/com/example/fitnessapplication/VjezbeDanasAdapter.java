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

public class VjezbeDanasAdapter extends RecyclerView.Adapter<VjezbeDanasAdapter.MyHolderVjezbaDanas> {

    Context context;
    ArrayList<Vjezba> vjezbaArrayLista;

    public VjezbeDanasAdapter(Context context, ArrayList<Vjezba> vjezbaArrayLista) {
        this.context = context;
        this.vjezbaArrayLista = vjezbaArrayLista;
    }

    @NonNull
    @Override
    public MyHolderVjezbaDanas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_vjezbedanaspoimenu, parent, false);
        return new MyHolderVjezbaDanas(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderVjezbaDanas holder, int position) {

        Vjezba vjezba = vjezbaArrayLista.get(position);
        holder.vjezbaDanasImeTxt.setText(vjezba.getImeVjezbe());

        holder.layoutVjezba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogvjezbepoimenu);

                TextView brojKalorija = dialog.findViewById(R.id.brojKalorija);
                TextView opis = dialog.findViewById(R.id.opis);
                Button btnZatvori = dialog.findViewById(R.id.zatvori);

                brojKalorija.setText(vjezba.getKalorije());
                opis.setText(vjezba.getOpis());
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
        return vjezbaArrayLista.size();
    }

    public static class MyHolderVjezbaDanas extends RecyclerView.ViewHolder{

        TextView vjezbaDanasImeTxt;
        LinearLayout layoutVjezba;

        public MyHolderVjezbaDanas(@NonNull View itemView) {
            super(itemView);

            vjezbaDanasImeTxt = itemView.findViewById(R.id.imeVjezbeDanas);
            layoutVjezba = itemView.findViewById(R.id.imeVjezbeLayout);
        }
    }

}
