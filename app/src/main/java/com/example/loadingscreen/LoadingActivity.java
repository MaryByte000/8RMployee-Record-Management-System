package com.example.loadingscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        ProgressBar progressBar = findViewById(R.id.progressBar);

        // Animate the progress bar from 0 to 100
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animation.setDuration(3000); // Animation duration in milliseconds (3 seconds)
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();

        // Delay the transition to your main activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Intent to start your main application screen
            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
            startActivity(intent);

            // Close the loading screen so the user can't go back to it
            finish();
        }, 3500); // Total delay in milliseconds (3.5 seconds)
    }
}