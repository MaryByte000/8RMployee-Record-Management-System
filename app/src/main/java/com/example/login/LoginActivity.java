package com.example.login;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView appNameTextView = findViewById(R.id.appName);
        String text = "8RMployee";

        SpannableString spannableString = new SpannableString(text);

        //colors for 8,RM, ployee
        int greenColor = Color.parseColor("#4CAF50");
        int blueColor = Color.parseColor("#1976D2");
        int yellowColor = Color.parseColor("#FFC107");
        spannableString.setSpan(new ForegroundColorSpan(greenColor), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(blueColor), 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(yellowColor), 3, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        appNameTextView.setText(spannableString);



    }
}