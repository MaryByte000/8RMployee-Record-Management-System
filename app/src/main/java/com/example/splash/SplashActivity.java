package com.example.splash;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.animation.ValueAnimator;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_TIMEOUT = 5000;
    private ShapeDrawable animatedBackground;
    private Matrix gradientMatrix;
    private LinearGradient gradient;
    private int gradientSize = 2000; // Large enough for smooth loop

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ConstraintLayout splashLayout = findViewById(R.id.splash_layout);

        animatedBackground = new ShapeDrawable(new RectShape());
        splashLayout.setBackground(animatedBackground);

        int[] colors = new int[]{
                0xFF1B4598, // Blue
                0xFF42B448, // Green
                0xFFF9CF4D, // Yellow
                0xFF1B4598  // Repeat Blue for smooth loop
        };

        gradient = new LinearGradient(
                gradientSize, gradientSize, // start from bottom-right
                0, 0,                       // move to top-left
                colors,
                null,
                Shader.TileMode.REPEAT
        );

        gradientMatrix = new Matrix();
        animatedBackground.getPaint().setShader(gradient);

        startDiagonalCarouselAnimation(splashLayout);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, SPLASH_TIMEOUT);
    }

    private void startDiagonalCarouselAnimation(View view) {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
        animator.setDuration(6000); // Adjust speed here
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());

        animator.addUpdateListener(animation -> {
            float progress = (float) animation.getAnimatedValue();
            float shift = -gradientSize * progress; // Negative for up-left motion

            gradientMatrix.setTranslate(shift, shift);
            gradient.setLocalMatrix(gradientMatrix);
            view.invalidate();
        });

        animator.start();
    }
}
