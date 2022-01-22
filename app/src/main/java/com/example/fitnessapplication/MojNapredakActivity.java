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

import java.util.Map;

public class MojNapredakActivity extends AppCompatActivity {

    private TextView trenutnaTezinaTxt;
    private TextView bmiTxt;
    private TextView izgubljeniKilogramiTxt;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moj_napredak);

        mAuth = FirebaseAuth.getInstance();


        trenutnaTezinaTxt = (TextView) findViewById(R.id.trenutnaTezina);
        bmiTxt = (TextView) findViewById(R.id.trenutniBmi);
        izgubljeniKilogramiTxt = (TextView) findViewById(R.id.izgubljeniKilogrami);

        databaseReference = FirebaseDatabase.getInstance().getReference("Napredak");

        databaseReference.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Napredak napredak = dataSnapshot.getValue(Napredak.class);

                    String trenutnaTezina = napredak.getTrenutnaTezina();
                    String bmi = napredak.getBmi();

                    trenutnaTezinaTxt.setText(trenutnaTezina);
                    bmiTxt.setText(bmi);


                    String trenutnaTezina2 = String.valueOf(dataSnapshot.child("trenutnaTezina").getValue());
                    int trenutnoKg = Integer.parseInt(trenutnaTezina2);

                    int rezultatTrenutnogVaganja = trenutnoKg;
                    vaganje(rezultatTrenutnogVaganja);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void vaganje(int rezultatTrenutnogVaganja) {
        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference("Korisnik");
        databaseReference2.child(mAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String pocetnaTezina = String.valueOf(dataSnapshot.child("tezina").getValue());
                int pocetnoKg = Integer.parseInt(pocetnaTezina);
                int izgubljeneKg = pocetnoKg - rezultatTrenutnogVaganja;

                izgubljeniKilogramiTxt.setText(String.valueOf(izgubljeneKg));





            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}