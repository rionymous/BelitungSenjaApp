/*
 * Created by Android Rion on 9/15/19 10:28 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/14/19 3:41 PM
 * Kunjungi androidrion.com untuk tutorial Android Studio
 */

package com.belitungsenja.travelapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tiket extends AppCompatActivity {

    DatabaseReference reference, reference2;

    TextView xnama_paket, xdurasi_paket, xjumlah_tamu, xtanggal_keberangkatan, xwaktu_keberangkatan;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tiket Trip");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUsernameLocal();

        xnama_paket = findViewById(R.id.xnama_paket);
        xdurasi_paket = findViewById(R.id.xdurasi_paket);
        xjumlah_tamu = findViewById(R.id.xjumlah_tamu);
        xtanggal_keberangkatan = findViewById(R.id.xtanggal_keberangkatan);
        xwaktu_keberangkatan = findViewById(R.id.xwaktu_keberangkatan);

        Bundle bundle = getIntent().getExtras();
        final String nama_paket_baru = bundle.getString("nama_paket");

        reference = FirebaseDatabase.getInstance().getReference().child("Paket").child(nama_paket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //xnama_paket.setText(dataSnapshot.child("nama_paket").getValue().toString());
                //xdurasi_paket.setText(dataSnapshot.child("durasi").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference2 = FirebaseDatabase.getInstance().getReference().child("Tickets").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //xjumlah_tamu.setText(dataSnapshot.child("jumlah_tamu").getValue().toString());
                //xtanggal_keberangkatan.setText(dataSnapshot.child("date").getValue().toString());
                //xwaktu_keberangkatan.setText(dataSnapshot.child("time").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }
}
