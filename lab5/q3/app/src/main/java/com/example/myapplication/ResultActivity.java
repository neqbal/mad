package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_result);

        TextView result = findViewById(R.id.resultText);

        String output = "Booking Details\n\n" +
                "Movie: " + getIntent().getStringExtra("movie") + "\n" +
                "Theatre: " + getIntent().getStringExtra("theatre") + "\n" +
                "Date: " + getIntent().getStringExtra("date") + "\n" +
                "Time: " + getIntent().getStringExtra("time") + "\n" +
                "Ticket Type: " + getIntent().getStringExtra("type") + "\n" +
                "Available Seats: " + getIntent().getIntExtra("seats", 0);

        result.setText(output);
    }
}