package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("XYZ Fitness Center\n\nWe provide top class training and fitness programs.");
        tv.setPadding(30,30,30,30);

        setContentView(tv);
    }
}