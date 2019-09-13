/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/13/19 6:38 PM
 */

package com.belitungsenja.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    DatabaseReference reference, reference2;

    ImageView photo_profile;
    TextView fullname, email, phonenumber, password;
    RecyclerView myticket_place;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    ArrayList<MyTicket> list;
    TicketAdapter ticketAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUsernameLocal();

        photo_profile = findViewById(R.id.photo_profile);
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        //password = findViewById(R.id.password);

        myticket_place = findViewById(R.id.myticket_place);
        myticket_place.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<MyTicket>();

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullname.setText(dataSnapshot.child("fullname").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                phonenumber.setText(dataSnapshot.child("phonenumber").getValue().toString());
                //password.setText(dataSnapshot.child("password").getValue().toString());
                Picasso.with(Profile.this).load(dataSnapshot.child("url_photo_profile")
                        .getValue().toString()).centerCrop().fit().into(photo_profile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference2 = FirebaseDatabase.getInstance().getReference().child("Tickets").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    MyTicket p = dataSnapshot1.getValue(MyTicket.class);
                    list.add(p);
                }
                ticketAdapter = new TicketAdapter(Profile.this, list);
                myticket_place.setAdapter(ticketAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void editprofile(View view) {
        Intent intent = new Intent(Profile.this, ProfileEdit.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, null);
                editor.apply();

                Intent intent = new Intent(Profile.this, GetStarted.class);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
