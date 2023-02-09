package com.example.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VjezbaActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    RecyclerView recyclerView;
    VjezbaAdapter vjezbaAdapter;
    ArrayList<Vjezba> vjezbaArrayList;
    Button btnDialogDodavanjeVjezbe;
    EditText brojKalorijaTxt;
    EditText imeVjezbeTxt;
    EditText opisTxt;
    Button btnDodajNovuVjezbu;
    Button btnOdustani;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezba);

        recyclerView = findViewById(R.id.popisVjezbi);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference("Vjezba");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        vjezbaArrayList = new ArrayList<>();
        vjezbaAdapter = new VjezbaAdapter(this, vjezbaArrayList);
        recyclerView.setAdapter(vjezbaAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Vjezba vjezba = dataSnapshot.getValue(Vjezba.class);
                    vjezbaArrayList.add(vjezba);
                }
                vjezbaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnDialogDodavanjeVjezbe = (Button) findViewById(R.id.dodajVjezbu);
        btnDialogDodavanjeVjezbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showDialog();
            }
        });

    }

    private void showDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View dodajVjezbu = layoutInflater.inflate(R.layout.dialogdodajvjezbu, null);

        brojKalorijaTxt = dodajVjezbu.findViewById(R.id.trosenjeKalorija);
        imeVjezbeTxt = dodajVjezbu.findViewById(R.id.imeVjezbe);
        opisTxt = dodajVjezbu.findViewById(R.id.opis);

        btnDodajNovuVjezbu = dodajVjezbu.findViewById(R.id.dodaj);
        btnOdustani = dodajVjezbu.findViewById(R.id.odustani);
        alertDialog.setView(dodajVjezbu);

        AlertDialog dialog = alertDialog.create();

        btnDodajNovuVjezbu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dodajNovuVjezbu();

            }
        });

        btnOdustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void dodajNovuVjezbu() {
        String kalorije = brojKalorijaTxt.getText().toString();
        String imeVjezbe = imeVjezbeTxt.getText().toString();
        String opis = opisTxt.getText().toString();

        if(!kalorije.isEmpty() && !imeVjezbe.isEmpty() && !opis.isEmpty()){
            String idVjezba = databaseReference.push().getKey();
            Vjezba vjezba = new Vjezba(idVjezba, kalorije, imeVjezbe, opis);

            databaseReference.child(idVjezba).setValue(vjezba).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    brojKalorijaTxt.setText("");
                    imeVjezbeTxt.setText("");
                    opisTxt.setText("");
                    Toast.makeText(VjezbaActivity.this, "Uspješno ste dodali novu vježbu!", Toast.LENGTH_SHORT).show();
                    Intent menuIntent = new Intent(getApplicationContext(), VjezbaActivity.class);
                    startActivity(menuIntent);
                    finish();
                }
            });
        }
        else {
            Toast.makeText(VjezbaActivity.this, "Unesite podatke!", Toast.LENGTH_LONG).show();
            return;

        }
    }
}