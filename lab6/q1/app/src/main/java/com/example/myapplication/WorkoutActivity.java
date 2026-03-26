package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);

        TextView tv = new TextView(this);
        tv.setText("Workout Plans:\n\n• Weight Loss\n• Cardio\n• Strength Training\n• Yoga");
        tv.setPadding(30,30,30,30);

        setContentView(tv);
    }
}