/*
 * Created by Triono Hidayat on 9/13/19 10:00 PM
 * Copyright © 2019 . All rights reserved.
 * Last modified 9/13/19 6:42 PM
 */

package com.belitungsenja.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getUsernameLocal();


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
        if (username_key_new.isEmpty()){
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreen.this, GetStarted.class);
                        startActivity(intent);

                        finish();
                    }
                }
            };
            timerThread.start();
        }
        else {
            Thread timerThread = new Thread() {
                public void run() {
                    try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                        startActivity(intent);

                        finish();
                    }
                }
            };
            timerThread.start();
        }
    }
}
