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

        String src = getIntent().getStringExtra("src");
        String dest = getIntent().getStringExtra("dest");
        String date = getIntent().getStringExtra("date");
        String trip = getIntent().getStringExtra("trip");

        String output = "Ticket Details\n\n" +
                "Source: " + src + "\n" +
                "Destination: " + dest + "\n" +
                "Date: " + date + "\n" +
                "Trip Type: " + trip;

        result.setText(output);
    }
}