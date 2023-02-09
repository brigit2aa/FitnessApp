package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NapredakPoDatumimaActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    NapredakPoDatumimaAdapter napredakPoDatumimaAdapter;
    ArrayList<Napredak> napredakArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_napredak_po_datumima);

        recyclerView = findViewById(R.id.popisNapredaka);
        databaseReference = FirebaseDatabase.getInstance().getReference("Napredak");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        napredakArrayList = new ArrayList<>();
        napredakPoDatumimaAdapter = new NapredakPoDatumimaAdapter(this, napredakArrayList);
        recyclerView.setAdapter(napredakPoDatumimaAdapter);

        mAuth = FirebaseAuth.getInstance();
        databaseReference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Napredak napredak = dataSnapshot.getValue(Napredak.class);
                    napredakArrayList.add(napredak);
                }
                napredakPoDatumimaAdapter.notifyDataSetChanged();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}