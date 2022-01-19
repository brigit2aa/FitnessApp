package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class IzbornikActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnMojNapredak;
    private Button btnDanas;
    private Button btnVjezbe;
    private Button btnRecept;
    private Button btnBmi;
    private Button btnDnevniUnos;
    private Button btnOdjava;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_izbornik);

        btnDnevniUnos = (Button) findViewById(R.id.dnevniUnos);
        btnDnevniUnos.setOnClickListener(this);
        btnMojNapredak = (Button) findViewById(R.id.mojnapredak);
        btnMojNapredak.setOnClickListener(this);
        btnDanas = (Button) findViewById(R.id.danas);
        btnDanas.setOnClickListener(this);
        btnVjezbe = (Button) findViewById(R.id.vjezbe);
        btnVjezbe.setOnClickListener(this);
        btnRecept = (Button) findViewById(R.id.recepti);
        btnRecept.setOnClickListener(this);
        btnBmi = (Button) findViewById(R.id.bmi);
        btnBmi.setOnClickListener(this);
        btnOdjava = (Button) findViewById(R.id.odjava);
        btnOdjava.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vjezbe:
                startActivity(new Intent(this, VjezbaActivity.class));
                break;
            case R.id.recepti:
                startActivity(new Intent(this, ReceptActivity.class));
                break;
            case R.id.bmi:
                startActivity(new Intent(this, BMIActivity.class));
                break;
            case R.id.odjava:
                mAuth.signOut();
                odjaviKorisnika();
                break;

        }
    }

    private void odjaviKorisnika() {
        Intent odjavaIntent = new Intent(IzbornikActivity.this, PocetniActivity.class);
        odjavaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(odjavaIntent);
        finish();
    }
}

//https://www.youtube.com/watch?v=kPcWNy7eMLI tu mi je logout, no vidjela sam manipuliranje podatcima