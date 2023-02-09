package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistracijaKorisnikaActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnRegistracija;
    private EditText imePrezimeTxt;
    private EditText visinaTxt;
    private EditText tezinaTxt;
    private EditText korisnockoImelTxt;
    private EditText lozinkaTxt;
    private TextView izracunajBmi;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registracijakorisnika);

        mAuth = FirebaseAuth.getInstance();

        imePrezimeTxt = (EditText) findViewById(R.id.imePrezime);
        visinaTxt = (EditText) findViewById(R.id.visina);
        tezinaTxt = (EditText) findViewById(R.id.tezina);
        korisnockoImelTxt = (EditText) findViewById(R.id.korisnickoime);
        lozinkaTxt = (EditText) findViewById(R.id.lozinka);
        btnRegistracija = (Button) findViewById(R.id.registrirajse);
        btnRegistracija.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrirajse:
                RegistracijaKorisnika();
                break;
        }

    }
    private void RegistracijaKorisnika() {

        String imePrezime = imePrezimeTxt.getText().toString();
        String visina = visinaTxt.getText().toString();
        String tezina = tezinaTxt.getText().toString();
        String email = korisnockoImelTxt.getText().toString();
        String password = lozinkaTxt.getText().toString();

       /*if(imePrezime.isEmpty()){
            imePrezimeTxt.setError("Upiši ime prezime");
            imePrezimeTxt.requestFocus();
            return;
        }

        if(imePrezime.isEmpty()){
            visinaTxt.setError("Upiši Vašu visinu");
            visinaTxt.requestFocus();
            return;
        }

        if(imePrezime.isEmpty()){
            tezinaTxt.setError("Upiši Vašu težinu");
            tezinaTxt.requestFocus();
            return;
        }

        if(email.isEmpty()){
            korisnockoImelTxt.setError("Upiši email adresu");
            korisnockoImelTxt.requestFocus();
            return;
        }*/

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            korisnockoImelTxt.setError("Korisnik nije registriran");
            korisnockoImelTxt.requestFocus();
            return;
        }

       /* if(password.isEmpty()){
            lozinkaTxt.setError("Uoiši loz");
            lozinkaTxt.requestFocus();
            return;
        }*/
        if(!imePrezime.isEmpty() && !visina.isEmpty() && !tezina.isEmpty() && !email.isEmpty() &&!password.isEmpty()) {
               mAuth.createUserWithEmailAndPassword(email, password)
                       .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                           @Override
                           public void onComplete(@NonNull Task<AuthResult> task) {
                               if (task.isSuccessful()) {
                                   Korisnik korisnik = new Korisnik(imePrezime, visina, tezina, email, password);

                                   FirebaseDatabase.getInstance().getReference("Korisnik")
                                           .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                           .setValue(korisnik).addOnCompleteListener(new OnCompleteListener<Void>() {
                                       @Override
                                       public void onComplete(@NonNull Task<Void> task) {
                                           if (task.isSuccessful()) {

                                               imePrezimeTxt.setText("");
                                               visinaTxt.setText("");
                                               tezinaTxt.setText("");
                                               korisnockoImelTxt.setText("");
                                               lozinkaTxt.setText("");
                                               Toast.makeText(RegistracijaKorisnikaActivity.this, "Uspjesno ste se registrirali", Toast.LENGTH_SHORT).show();
                                           }
                                       }
                                   });
                               }
                           }
                       });
        }
        else {
            Toast.makeText(RegistracijaKorisnikaActivity.this, "Unesite podatke!", Toast.LENGTH_LONG).show();
            return;
        }
    }
}