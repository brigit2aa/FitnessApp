package com.example.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PocetniActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private Button btnRegistracija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetni);

        btnLogin = (Button) findViewById(R.id.prijavise);
        btnLogin.setOnClickListener(this);
        btnRegistracija = (Button) findViewById(R.id.registracija);
        btnRegistracija.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prijavise:
                startActivity(new Intent(this, PrijavaActivity.class));
                break;
            case R.id.registracija:
                startActivity(new Intent(this, RegistracijaKorisnikaActivity.class));
                break;
        }
    }
}