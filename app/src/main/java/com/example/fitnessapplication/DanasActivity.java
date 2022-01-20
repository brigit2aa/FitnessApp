package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DanasActivity extends AppCompatActivity {

    private TextView trenutnaTezinaTxt;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danas);

        trenutnaTezinaTxt = (TextView) findViewById(R.id.tezina);

        databaseReference = FirebaseDatabase.getInstance().getReference("Korisnik");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Korisnik korisnik = dataSnapshot.getValue(Korisnik.class);

                    String trenutnaTezina = korisnik.getTrenutnaTezina();

                    trenutnaTezinaTxt.setText(trenutnaTezina);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}