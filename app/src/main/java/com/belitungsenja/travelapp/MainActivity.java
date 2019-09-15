/*
 * Created by Android Rion on 9/15/19 10:28 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/15/19 10:27 PM
 * Kunjungi androidrion.com untuk tutorial Android Studio
 */

package com.belitungsenja.travelapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_STEP = 3;
    private int current_step = 1;

    TextView bio, fullname, balance,
            judulpopuler1, judulpopuler2, judulpopuler3,
            durasipopuler1, durasipopuler2, durasipopuler3,
            judulhemat, judulreguler, judulkeluarga, judulhoneymoon,
            titleBlog1, titleBlog2, titleBlog3, testimonial;
    ImageView photo_profile,
            gambarpopuler1, gambarpopuler2, gambarpopuler3,
            gambarhemat, gambarreguler, gambarkeluarga, gambarhoneymoon,
            imageBlog1, imageBlog2, imageBlog3, kanantesti, kiritesti;

    DatabaseReference reference,
            referencepopuler1, referencepopuler2, referencepopuler3,
            referenceHemat, referenceReguler, referenceKeluarga, referenceHoneymoon,
            referenceBlog1, referenceBlog2, referenceBlog3;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar();

        getUsernameLocal();

        fullname = findViewById(R.id.fullname);
        bio = findViewById(R.id.bio);
        balance = findViewById(R.id.balance);
        photo_profile = findViewById(R.id.photo_profile);

        judulpopuler1 = findViewById(R.id.judulpopuler1);
        durasipopuler1 = findViewById(R.id.durasipopuler1);
        gambarpopuler1 = findViewById(R.id.gambarpopuler1);
        judulpopuler2 = findViewById(R.id.judulpopuler2);
        durasipopuler2 = findViewById(R.id.durasipopuler2);
        gambarpopuler2 = findViewById(R.id.gambarpopuler2);
        judulpopuler3 = findViewById(R.id.judulpopuler3);
        durasipopuler3 = findViewById(R.id.durasipopuler3);
        gambarpopuler3 = findViewById(R.id.gambarpopuler3);

        judulhemat = findViewById(R.id.judulhemat);
        gambarhemat = findViewById(R.id.gambarHemat);
        judulreguler = findViewById(R.id.judulreguler);
        gambarreguler = findViewById(R.id.gambarReguler);
        judulkeluarga = findViewById(R.id.judulkeluarga);
        gambarkeluarga = findViewById(R.id.gambarKeluarga);
        judulhoneymoon = findViewById(R.id.judulhoneymoon);
        gambarhoneymoon = findViewById(R.id.gambarHoneymoon);

        kanantesti = findViewById(R.id.kanantesti);
        testimonial = findViewById(R.id.testimonial);
        kiritesti = findViewById(R.id.kiritesti);

        titleBlog1 = findViewById(R.id.titleBlog1);
        imageBlog1 = findViewById(R.id.imageBlog1);
        titleBlog2 = findViewById(R.id.titleBlog2);
        imageBlog2 = findViewById(R.id.imageBlog2);
        titleBlog3 = findViewById(R.id.titleBlog3);
        imageBlog3 = findViewById(R.id.imageBlog3);

        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fullname.setText(dataSnapshot.child("fullname").getValue().toString());
                bio.setText(dataSnapshot.child("bio").getValue().toString());
                balance.setText(dataSnapshot.child("balance").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(photo_profile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referencepopuler1 = FirebaseDatabase.getInstance().getReference().child("Populer").child("Populer1");
        referencepopuler1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulpopuler1.setText(dataSnapshot.child("nama_paket").getValue().toString());
                durasipopuler1.setText(dataSnapshot.child("durasi").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarpopuler1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referencepopuler2 = FirebaseDatabase.getInstance().getReference().child("Populer").child("Populer2");
        referencepopuler2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulpopuler2.setText(dataSnapshot.child("nama_paket").getValue().toString());
                durasipopuler2.setText(dataSnapshot.child("durasi").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarpopuler2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referencepopuler3 = FirebaseDatabase.getInstance().getReference().child("Populer").child("Populer3");
        referencepopuler3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulpopuler3.setText(dataSnapshot.child("nama_paket").getValue().toString());
                durasipopuler3.setText(dataSnapshot.child("durasi").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarpopuler3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceHemat = FirebaseDatabase.getInstance().getReference().child("Paket").child("Hemat4d3n");
        referenceHemat.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulhemat.setText(dataSnapshot.child("nama_paket").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarhemat);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceReguler = FirebaseDatabase.getInstance().getReference().child("Paket").child("Reguler4d3n");
        referenceReguler.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulreguler.setText(dataSnapshot.child("nama_paket").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarreguler);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceKeluarga = FirebaseDatabase.getInstance().getReference().child("Paket").child("Keluarga4d3n");
        referenceKeluarga.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulkeluarga.setText(dataSnapshot.child("nama_paket").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarkeluarga);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceHoneymoon = FirebaseDatabase.getInstance().getReference().child("Paket").child("Honeymoon4d3n");
        referenceHoneymoon.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                judulhoneymoon.setText(dataSnapshot.child("nama_paket").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(gambarhoneymoon);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceBlog1 = FirebaseDatabase.getInstance().getReference().child("Blog").child("blog1");
        referenceBlog1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titleBlog1.setText(dataSnapshot.child("judulBlog").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("gambarBlog")
                                .getValue().toString()).centerCrop().fit().into(imageBlog1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceBlog2 = FirebaseDatabase.getInstance().getReference().child("Blog").child("blog2");
        referenceBlog2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titleBlog2.setText(dataSnapshot.child("judulBlog").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("gambarBlog")
                                .getValue().toString()).centerCrop().fit().into(imageBlog2);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        referenceBlog3 = FirebaseDatabase.getInstance().getReference().child("Blog").child("blog3");
        referenceBlog3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                titleBlog3.setText(dataSnapshot.child("judulBlog").getValue().toString());
                Picasso.with(MainActivity.this)
                        .load(dataSnapshot.child("gambarBlog")
                                .getValue().toString()).centerCrop().fit().into(imageBlog3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        kanantesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextStep(current_step);
                bottomProgressDots(current_step);
            }
        });

        kiritesti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backStep(current_step);
                bottomProgressDots(current_step);
            }
        });

        String str_progress = String.format(getString(R.string.step_of), current_step, MAX_STEP);
        testimonial.setText(str_progress);
        bottomProgressDots(current_step);
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }

    private void toolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Belitung Senja");
    }

    public void gotoprofile(View view) {
        Intent intent = new Intent(MainActivity.this, Profile.class);
        startActivity(intent);
    }

    public void populer1(View view) {
        Intent intent = new Intent(MainActivity.this, InfoPaket.class);
        intent.putExtra("populer", "Hemat3d2n");
        startActivity(intent);
    }

    public void populer2(View view) {
        Intent intent = new Intent(MainActivity.this, InfoPaket.class);
        intent.putExtra("populer", "Keluarga4d3n");
        startActivity(intent);
    }

    public void populer3(View view) {
        Intent intent = new Intent(MainActivity.this, InfoPaket.class);
        intent.putExtra("populer", "Honeymoon3d2n");
        startActivity(intent);
    }

    public void paketwisatahemat(View view) {
        Intent kepakethemat = new Intent(MainActivity.this, KategoriPaket.class);
        kepakethemat.putExtra("jenis_paket", "Hemat");
        startActivity(kepakethemat);
    }

    public void paketwisatareguler(View view) {
        Intent kepaketreguler = new Intent(MainActivity.this, KategoriPaket.class);
        kepaketreguler.putExtra("jenis_paket", "Reguler");
        startActivity(kepaketreguler);
    }

    public void kepakethoneymoon(View view) {
        Intent kepakethoneymoon = new Intent(MainActivity.this, KategoriPaket.class);
        kepakethoneymoon.putExtra("jenis_paket", "Honeymoon");
        startActivity(kepakethoneymoon);
    }

    public void kepaketkeluarga(View view) {
        Intent kepaketkeluarga = new Intent(MainActivity.this, KategoriPaket.class);
        kepaketkeluarga.putExtra("jenis_paket", "Keluarga");
        startActivity(kepaketkeluarga);
    }


    private void backStep(int progress) {
        if (progress > 1) {
            progress--;
            current_step = progress;
            ViewAnimation.fadeOutIn(testimonial);
        }
        String str_progress = String.format(getString(R.string.step_of), current_step, MAX_STEP);
        testimonial.setText(str_progress);
    }

    private void nextStep(int progress) {
        if (progress < MAX_STEP) {
            progress++;
            current_step = progress;
            ViewAnimation.fadeOutIn(testimonial);
        }
        String str_progress = String.format(getString(R.string.step_of), current_step, MAX_STEP);
        testimonial.setText(str_progress);
    }

    private void bottomProgressDots(int current_index) {
        current_index--;
        LinearLayout dotsLayout = findViewById(R.id.layoutDots);
        ImageView[] dots = new ImageView[MAX_STEP];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new ImageView(this);
            int width_height = 15;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(width_height, width_height));
            params.setMargins(10, 10, 10, 10);
            dots[i].setLayoutParams(params);
            dots[i].setImageResource(R.drawable.shape_circle);
            dots[i].setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_IN);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[current_index].setImageResource(R.drawable.shape_circle);
            dots[current_index].setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        }
    }

    public void blog1(View view) {
        Intent intent = new Intent(MainActivity.this, Blog.class);
        intent.putExtra("artikel_blog", "blog1");
        startActivity(intent);
    }

    public void blog2(View view) {
        Intent intent = new Intent(MainActivity.this, Blog.class);
        intent.putExtra("artikel_blog", "blog2");
        startActivity(intent);
    }

    public void blog3(View view) {
        Intent intent = new Intent(MainActivity.this, Blog.class);
        intent.putExtra("artikel_blog", "blog3");
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Anda Yakin Untuk Keluar?");
        builder.setCancelable(true);
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
