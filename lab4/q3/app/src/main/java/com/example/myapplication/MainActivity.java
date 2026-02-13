package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    ImageView modeImage;
    Button changeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Prevent layout from hiding under notification panel
        getWindow().setDecorFitsSystemWindows(true);

        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleMode);
        modeImage = findViewById(R.id.modeImage);
        changeButton = findViewById(R.id.btnChangeMode);

        // Handle ToggleButton state change
        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            updateMode(isChecked);
        });

        // Handle Change Mode button click
        changeButton.setOnClickListener(v -> {
            toggleButton.setChecked(!toggleButton.isChecked());
        });

        // Initialize default mode
        updateMode(toggleButton.isChecked());
    }

    private void updateMode(boolean isWifi) {

        if (isWifi) {
            modeImage.setImageResource(R.drawable.wifi);
            Toast.makeText(this, "Current Mode: Wi-Fi", Toast.LENGTH_SHORT).show();
        } else {
            modeImage.setImageResource(R.drawable.mobile_data);
            Toast.makeText(this, "Current Mode: Mobile Data", Toast.LENGTH_SHORT).show();
        }
    }
}