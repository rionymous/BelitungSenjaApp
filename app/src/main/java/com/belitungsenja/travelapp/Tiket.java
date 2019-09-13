/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/13/19 5:18 PM
 */

package com.belitungsenja.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tiket extends AppCompatActivity {

    DatabaseReference reference, reference2;

    TextView xxnama_paket, xxdurasi_paket, xjumlah_tamu, xtanggal_keberangkatan, xwaktu_keberangkatan;

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

        xxnama_paket = findViewById(R.id.xxnama_paket);
        xxdurasi_paket = findViewById(R.id.xxdurasi_paket);
        xjumlah_tamu = findViewById(R.id.xjumlah_tamu);
        xtanggal_keberangkatan = findViewById(R.id.xtanggal_keberangkatan);
        xwaktu_keberangkatan = findViewById(R.id.xwaktu_keberangkatan);

        Bundle bundle = getIntent().getExtras();
        final String nama_paket_baru = bundle.getString("nama_paket");

        reference = FirebaseDatabase.getInstance().getReference().child("Paket").child(nama_paket_baru);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xxnama_paket.setText(dataSnapshot.child("nama_paket").getValue().toString());
                xxdurasi_paket.setText(dataSnapshot.child("durasi").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*reference2 = FirebaseDatabase.getInstance().getReference().child("Tickets").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                xjumlah_tamu.setText(dataSnapshot.child("jumlah_tamu").getValue().toString());
                xtanggal_keberangkatan.setText(dataSnapshot.child("date").getValue().toString());
                xwaktu_keberangkatan.setText(dataSnapshot.child("time").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
