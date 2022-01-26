package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VjezbePoImenuActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    VjezbeDanasAdapter vjezbeDanasAdapter;
    ArrayList<Vjezba> vjezbaArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezbe_po_imenu);

        recyclerView = findViewById(R.id.popisVjezbiPoImenu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Vjezba");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vjezbaArrayList = new ArrayList<>();
        vjezbeDanasAdapter = new VjezbeDanasAdapter(this, vjezbaArrayList);
        recyclerView.setAdapter(vjezbeDanasAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Vjezba vjezba = dataSnapshot.getValue(Vjezba.class);
                    vjezbaArrayList.add(vjezba);
                }
                vjezbeDanasAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}