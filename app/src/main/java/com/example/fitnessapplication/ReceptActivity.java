package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReceptActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    ReceptAdapter receptAdapter;
    ArrayList<Recept> receptArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recept);

        recyclerView = findViewById(R.id.popisRecepata);
        databaseReference = FirebaseDatabase.getInstance().getReference("Recept");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        receptArrayList = new ArrayList<>();
        receptAdapter = new ReceptAdapter(this, receptArrayList);
        recyclerView.setAdapter(receptAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    Recept recept = dataSnapshot.getValue(Recept.class);
                    receptArrayList.add(recept);
                }
                receptAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}