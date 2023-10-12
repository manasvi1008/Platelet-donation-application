package com.example.plateletapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    // Duration of splash screen in milliseconds
    private static final int SPLASH_SCREEN_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Delayed execution of code after the splash screen duration
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity or any other activity after the splash screen
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the splash screen activity
            }
        }, SPLASH_SCREEN_DURATION);
    }
}
