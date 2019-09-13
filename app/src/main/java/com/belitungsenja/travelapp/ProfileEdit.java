/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/13/19 6:30 PM
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
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileEdit extends AppCompatActivity {

    EditText fullname, username, bio, email, phonenumber, password;
    Button btnSave;
    ImageView photo_profile;

    DatabaseReference reference;
    StorageReference storage;

    Uri photo_location;
    Integer photo_max = 1;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUsernameLocal();

        photo_profile = findViewById(R.id.photo_profile);
        fullname = findViewById(R.id.fullname);
        username = findViewById(R.id.username);
        bio = findViewById(R.id.bio);
        phonenumber = findViewById(R.id.phonenumber);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSave = findViewById(R.id.btnSave);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        storage = FirebaseStorage.getInstance().getReference().child("Photousers").child(username_key_new);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullname.setText(dataSnapshot.child("fullname").getValue().toString());
                username.setText(dataSnapshot.child("username").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                phonenumber.setText(dataSnapshot.child("phonenumber").getValue().toString());
                email.setText(dataSnapshot.child("email").getValue().toString());
                password.setText(dataSnapshot.child("password").getValue().toString());
                Picasso.with(ProfileEdit.this).load(dataSnapshot.child("url_photo_profile")
                        .getValue().toString()).centerCrop().fit().into(photo_profile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        photo_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhoto();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnSave.setEnabled(false);
                btnSave.setText("Loading ...");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference.getRef().child("fullname").setValue(fullname.getText().toString());
                        reference.getRef().child("username").setValue(username.getText().toString());
                        reference.getRef().child("bio").setValue(bio.getText().toString());
                        reference.getRef().child("phonenumber").setValue(phonenumber.getText().toString());
                        reference.getRef().child("email").setValue(email.getText().toString());
                        reference.getRef().child("password").setValue(password.getText().toString());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                if (photo_location != null) {
                    final StorageReference storageReference1 = storage.child(System.currentTimeMillis() + "." + getFileExtension(photo_location));
                    storageReference1.putFile(photo_location).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String uri_photo = uri.toString();
                                    reference.getRef().child("url_photo_profile").setValue(uri_photo);
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                }
                            });

                        }
                    });
                }
                Intent intent = new Intent(ProfileEdit.this, Profile.class);
                startActivity(intent);
            }
        });

    }

    String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    public void findPhoto() {
        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null) {
            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(photo_profile);
        }
    }


    private void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
