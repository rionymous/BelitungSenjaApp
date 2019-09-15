/*
 * Created by Android Rion on 9/15/19 10:28 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/15/19 10:27 PM
 * Kunjungi androidrion.com untuk tutorial Android Studio
 */

package com.belitungsenja.travelapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Blog extends AppCompatActivity {

    DatabaseReference reference;
    //private TextView msg_url;
    //private WebView web_view;
    //private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    //private DatabaseReference reference = firebaseDatabase.getReference();
    //private DatabaseReference childReference = reference.child("Blog").child("url");*/
    private WebView web_view;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Blog");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //msg_url = findViewById(R.id.webUrl);
        web_view = findViewById(R.id.webView);
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        //String url = getIntent().getExtras().getString("url");
        //web_view.loadUrl(url);

        Bundle bundle = getIntent().getExtras();
        final String blog_baru = bundle.getString("artikel_blog");
        reference = FirebaseDatabase.getInstance().getReference().child("Blog").child(blog_baru);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String url = dataSnapshot.getValue(String.class);
                //msg_url.setText(url);
                web_view.loadUrl(dataSnapshot.child("url").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
