package com.example.dog;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.imageView);
        TextView textView = findViewById(R.id.textView);


        // Fade in the ImageView
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView, "alpha", 0f, 1f);
        fadeIn.setDuration(1500);

        // Slide up animation for TextView
        ObjectAnimator slideUp1 = ObjectAnimator.ofFloat(textView, "translationY", 100f, 0f);
        slideUp1.setDuration(1000);

        // Slide up animation for another TextView


        // Run animations simultaneously
        fadeIn.start();
        slideUp1.start();


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After the timeout, start your main activity
                Intent mainIntent = new Intent(splash.this,Select_option.class);
                startActivity(mainIntent);
                finish();
            }
        }, 2000);
    }
}
