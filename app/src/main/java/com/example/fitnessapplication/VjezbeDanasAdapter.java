package com.example.fitnessapplication;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("OdvjezbaneVjezbe");
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        String id = databaseReference.push().getKey();
        holder.gotovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(mAuth.getUid()).child(id).setValue(vjezba).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });

            }
        });
        holder.layoutVjezba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.dialogvjezbepoimenu);

                TextView opis = dialog.findViewById(R.id.opis);
                TextView brKalorijaTxt = dialog.findViewById(R.id.brKalorija);

                Button btnZatvori = dialog.findViewById(R.id.zatvori);

                opis.setText(vjezba.getOpis());
                brKalorijaTxt.setText(vjezba.getKalorije());
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
        Button gotovo;

        public MyHolderVjezbaDanas(@NonNull View itemView) {
            super(itemView);

            vjezbaDanasImeTxt = itemView.findViewById(R.id.imeVjezbeDanas);

            layoutVjezba = itemView.findViewById(R.id.imeVjezbeLayout);
            gotovo = itemView.findViewById(R.id.odvjezbano);
        }
    }

}
