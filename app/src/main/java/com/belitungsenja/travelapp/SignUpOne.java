/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/11/19 1:20 PM
 */

package com.belitungsenja.travelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SignUpOne extends AppCompatActivity {

    EditText username, email, password;
    Button btnContinue;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_one);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register to Belitung Senja");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, username.getText().toString());
                editor.apply();

                //Toast.makeText(getApplicationContext(), "Username " + username.getText().toString(), Toast.LENGTH_SHORT).show();

                reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username.getText().toString());

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("username").setValue(username.getText().toString());
                        dataSnapshot.getRef().child("email").setValue(email.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(password.getText().toString());
                        dataSnapshot.getRef().child("balance").setValue(2000000);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(SignUpOne.this, SignUpTwo.class);
                startActivity(intent);
            }
        });
    }

    public void signIn(View view) {
        Intent intent = new Intent(SignUpOne.this, SignIn.class);
        startActivity(intent);
    }
}
