package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DanasActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView trenutnaTezinaTxt, kalorijeTxt;
    private Button btnVjezbeDanas;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    RecyclerView recyclerView;
    private LayoutInflater layoutInflater;
    VjezbeDanasAdapter vjezbeDanasAdapter;
    ArrayList<Vjezba> vjezbaArrayList;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danas);

        mAuth = FirebaseAuth.getInstance();

        trenutnaTezinaTxt = (TextView) findViewById(R.id.tezina);
        kalorijeTxt = (TextView)  findViewById(R.id.potroseneKalorije);
        btnVjezbeDanas = (Button) findViewById(R.id.vjezbeDanas);


        databaseReference = FirebaseDatabase.getInstance().getReference("Napredak");

        databaseReference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Napredak napredak = dataSnapshot.getValue(Napredak.class);
                    String trenutnaTezina = napredak.getTrenutnaTezina();

                    trenutnaTezinaTxt.setText(trenutnaTezina);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference("OdvjezbaneVjezbe");

        databaseReference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Vjezba vjezba = dataSnapshot.getValue(Vjezba.class);
                    String trenutneKalorije = vjezba.getKalorije();

                    kalorijeTxt.setText(trenutneKalorije);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnVjezbeDanas.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vjezbeDanas:
                startActivity(new Intent(this,VjezbePoImenuActivity.class));
                break;
        }
    }
}