package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DanasActivity extends AppCompatActivity {

    private TextView trenutnaTezinaTxt;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danas);

        mAuth = FirebaseAuth.getInstance();

        trenutnaTezinaTxt = (TextView) findViewById(R.id.tezina);

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
    }
}