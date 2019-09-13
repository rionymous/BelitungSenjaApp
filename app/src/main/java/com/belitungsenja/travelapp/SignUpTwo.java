/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/12/19 5:43 AM
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SignUpTwo extends AppCompatActivity {

    EditText fullname, phonenumber, bio;
    ImageView pic_photo_register_user;
    Button btnCreateAccount;

    Uri photo_location;
    Integer photo_max = 1;

    DatabaseReference reference;
    StorageReference storage;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_two);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register to Belitung Senja");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getUsernameLocal();

        fullname = findViewById(R.id.fullname);
        bio = findViewById(R.id.bio);
        phonenumber = findViewById(R.id.phonenumber);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        pic_photo_register_user = findViewById(R.id.pic_photo_register_user);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnCreateAccount.setEnabled(false);
                btnCreateAccount.setText("Loading ...");

                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(username_key_new);
                storage = FirebaseStorage.getInstance().getReference().child("Photousers").child(username_key_new);

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
                                    reference.getRef().child("fullname").setValue(fullname.getText().toString());
                                    reference.getRef().child("phonenumber").setValue(phonenumber.getText().toString());
                                    reference.getRef().child("bio").setValue(bio.getText().toString());
                                }
                            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    Intent intent = new Intent(SignUpTwo.this, SignUpSuccess.class);
                                    startActivity(intent);
                                }
                            });

                        }
                    });
                }
            }
        });

        pic_photo_register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhoto();
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
            Picasso.with(this).load(photo_location).centerCrop().fit().into(pic_photo_register_user);
        }
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
