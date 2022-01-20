package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DnevniUnosActivity extends AppCompatActivity {

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

        //databaseReference = FirebaseDatabase.getInstance().getReference();
        trenutnoKgTxt = (EditText) findViewById(R.id.trenutnaTezinaUnos);
        bmiTxt = (EditText) findViewById(R.id.trenutniBmiUnos);
        btnUpisi = (Button) findViewById(R.id.spremi);


        btnUpisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String trenutnoKg = trenutnoKgTxt.getText().toString();
                String bmi = bmiTxt.getText().toString();
                unosZaDanas(trenutnoKg, bmi);
            }
        });
    }

    private void unosZaDanas(String trenutnoKg, String bmi) {
        HashMap Korisnik = new HashMap();
        Korisnik.put("trenutnaTezina", trenutnoKg);
        Korisnik.put("bmi", bmi);

        databaseReference = FirebaseDatabase.getInstance().getReference("Korisnik");
        databaseReference.child(mAuth.getUid()).updateChildren(Korisnik).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()){
                    trenutnoKgTxt.setText("");
                    bmiTxt.setText("");
                    Toast.makeText(DnevniUnosActivity.this, "Uspješno ste upisali podatke", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
//https://www.geeksforgeeks.org/user-authentication-using-firebase-in-android/
//https://stackoverflow.com/questions/58594142/how-to-write-code-that-called-your-database-child-for-firebase
//https://stackoverflow.com/questions/61186173/firebase-realtime-databse-updating-multiple-tables