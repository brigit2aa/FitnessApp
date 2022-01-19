package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PrijavaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogin;
    private EditText emailTxt;
    private EditText lozinkaTxt;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijava);

        emailTxt = (EditText) findViewById(R.id.korisnickoime);
        lozinkaTxt = (EditText) findViewById(R.id.lozinka);

        btnLogin = (Button) findViewById(R.id.prijavise);
        btnLogin.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.prijavise:
                prijaviKorisnika();
                break;
        }

    }
    private void prijaviKorisnika() {

        String email = emailTxt.getText().toString();
        String password = lozinkaTxt.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(PrijavaActivity.this, "Zaboravili ste upisati Vaše korisničko ime!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(PrijavaActivity.this, "Zaboravili ste upisati lozinku!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTxt.setError("Korisnik ne postoji!");
            emailTxt.requestFocus();
            return;
        }



        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    emailTxt.setText("");
                    lozinkaTxt.setText("");
                    startActivity(new Intent(PrijavaActivity.this, IzbornikActivity.class));
                }
                else {
                    Toast.makeText(PrijavaActivity.this, "Pogreška prilikom prijave! Provjerite svoje podatke!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


}