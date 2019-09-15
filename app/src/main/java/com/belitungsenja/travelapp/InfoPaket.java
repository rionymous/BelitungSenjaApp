/*
 * Created by Android Rion on 9/15/19 10:28 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/15/19 10:27 PM
 * Kunjungi androidrion.com untuk tutorial Android Studio
 */

package com.belitungsenja.travelapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class InfoPaket extends AppCompatActivity {

    DatabaseReference reference;

    ImageView gambarPaket;
    TextView namaPaket, durasiPaket, hargaPaket, deskripsiPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_paket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //////////////////////////////////////////////////////

        gambarPaket = findViewById(R.id.gambarPaket);
        namaPaket = findViewById(R.id.namaPaket);
        durasiPaket = findViewById(R.id.durasiPaket);
        hargaPaket = findViewById(R.id.hargaPaket);
        deskripsiPaket = findViewById(R.id.deskripsiPaket);

        Bundle bundle = getIntent().getExtras();
        final String jenis_tiket_baru = bundle.getString("populer");

        reference = FirebaseDatabase.getInstance().getReference().child("Paket").child(jenis_tiket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                namaPaket.setText(dataSnapshot.child("nama_paket").getValue().toString());
                durasiPaket.setText(dataSnapshot.child("durasi").getValue().toString());
                hargaPaket.setText(dataSnapshot.child("harga").getValue().toString());
                deskripsiPaket.setText(dataSnapshot.child("deksripsi").getValue().toString());
                namaPaket.setText(dataSnapshot.child("nama_paket").getValue().toString());
                Picasso.with(InfoPaket.this)
                        .load(dataSnapshot.child("url_thumbnail")
                        .getValue().toString()).centerCrop().fit()
                        .into(gambarPaket);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Toast.makeText(getApplicationContext(),"Jenis Paket " + jenis_tiket_baru, Toast.LENGTH_SHORT).show();

        Button button = findViewById(R.id.btn_booking);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoPaket.this, Booking.class);
                intent.putExtra("jenis_paket",jenis_tiket_baru);
                startActivity(intent);
            }
        });
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
