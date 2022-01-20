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

        databaseReference = FirebaseDatabase.getInstance().getReference("Korisnik");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Korisnik korisnik = dataSnapshot.getValue(Korisnik.class);

                    String trenutnaTezina = korisnik.getTrenutnaTezina();
                    String bmi = korisnik.getBmi();

                    //mAuth.getCurrentUser().getUid();
                    trenutnaTezinaTxt.setText(trenutnaTezina);
                    bmiTxt.setText(bmi);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Map<String, Object> map1 = (Map<String, Object>) dataSnapshot1.getValue();
                    Object pocetnoKg = map1.get("tezina");
                    int pocetno = Integer.parseInt(String.valueOf(pocetnoKg));

                    Map<String, Object> map2 = (Map<String, Object>) dataSnapshot1.getValue();
                    Object zavrsnoKg = map2.get("trenutnaTezina");
                    int zavrsno = Integer.parseInt(String.valueOf(zavrsnoKg));

                    int rezultat = pocetno-zavrsno;

                    if(pocetno > zavrsno){
                        izgubljeniKilogramiTxt.setText(String.valueOf("- " + rezultat + " kg"));
                    }
                    else {
                        izgubljeniKilogramiTxt.setText(String.valueOf("+ " + rezultat  + " kg"));
                    }


                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }
}