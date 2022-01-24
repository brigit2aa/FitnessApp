package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DnevniUnosActivity extends AppCompatActivity {

    private EditText datumTxt;
    private EditText trenutnoKgTxt;
    private EditText bmiTxt;
    private Button btnUpisi;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnevni_unos);

        mAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference("Napredak");

        datumTxt = (EditText) findViewById(R.id.trenutniDatumUnos);
        trenutnoKgTxt = (EditText) findViewById(R.id.trenutnaTezinaUnos);
        bmiTxt = (EditText) findViewById(R.id.trenutniBmiUnos);
        btnUpisi = (Button) findViewById(R.id.spremi);


        btnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                unosZaDanas();
            }
        });
    }

    private void unosZaDanas() {
        String datum = datumTxt.getText().toString();
        String trenutnaTezina = trenutnoKgTxt.getText().toString();
        String bmi = bmiTxt.getText().toString();

        if(!datum.isEmpty() && !trenutnaTezina.isEmpty() && !bmi.isEmpty()){
            String id = databaseReference.push().getKey();

            Napredak napredak = new Napredak(datum, trenutnaTezina, bmi);

            databaseReference.child(mAuth.getUid()).child(id).setValue(napredak).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    datumTxt.setText("");
                    trenutnoKgTxt.setText("");
                    bmiTxt.setText("");
                    Toast.makeText(DnevniUnosActivity.this, "Uspješno ste upisali podatke!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            Toast.makeText(this, "Unesite podatke!", Toast.LENGTH_LONG).show();
        }
    }
}
