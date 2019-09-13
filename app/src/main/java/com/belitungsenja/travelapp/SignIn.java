/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/12/19 4:40 PM
 */

package com.belitungsenja.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText xusername, xpassword;
    Button btnSignIn;

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Toolbar();

        xusername = findViewById(R.id.loginusername);
        xpassword = findViewById(R.id.loginpassword);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String username = xusername.getText().toString();
                final String password = xpassword.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                    btnSignIn.setEnabled(true);
                    btnSignIn.setText("Sign In");
                }
                else {
                    if (password.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                        btnSignIn.setEnabled(true);
                        btnSignIn.setText("Sign In");
                    }
                    else {
                        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username);
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {

                                    String passwordfirebase = dataSnapshot.child("password").getValue().toString();

                                    if (password.equals(passwordfirebase)) {

                                        btnSignIn.setEnabled(false);
                                        btnSignIn.setText("Loading ...");

                                        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();
                                        editor.putString(username_key, xusername.getText().toString());
                                        editor.apply();

                                        Intent intent = new Intent(SignIn.this, MainActivity.class);
                                        startActivity(intent);

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Password salah!", Toast.LENGTH_SHORT).show();
                                        btnSignIn.setEnabled(true);
                                        btnSignIn.setText("Sign In");
                                    }

                                } else {
                                    Toast.makeText(getApplicationContext(), "Username tidak terdaftar!", Toast.LENGTH_SHORT).show();
                                    btnSignIn.setEnabled(true);
                                    btnSignIn.setText("Sign In");
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });
    }

    private void Toolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sign In to Belitung Senja");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void forgetPassword(View view) {
        Intent intent = new Intent(SignIn.this, ForgotPassword.class);
        startActivity(intent);
    }

    public void signUp(View view) {
        Intent intent = new Intent(SignIn.this, SignUpOne.class);
        startActivity(intent);
    }
}
