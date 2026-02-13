package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private TextView redLabel, greenLabel, blueLabel;
    private View mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main);
        redSeekBar = findViewById(R.id.red_seekbar);
        greenSeekBar = findViewById(R.id.green_seekbar);
        blueSeekBar = findViewById(R.id.blue_seekbar);

        redLabel = findViewById(R.id.red_label);
        greenLabel = findViewById(R.id.green_label);
        blueLabel = findViewById(R.id.blue_label);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateBackgroundColor();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not needed for this app
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not needed for this app
            }
        };

        redSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        updateBackgroundColor(); // Set initial color
    }

    private void updateBackgroundColor() {
        int red = redSeekBar.getProgress();
        int green = greenSeekBar.getProgress();
        int blue = blueSeekBar.getProgress();

        redLabel.setText("Red: " + red);
        greenLabel.setText("Green: " + green);
        blueLabel.setText("Blue: " + blue);

        mainLayout.setBackgroundColor(Color.rgb(red, green, blue));
    }
}
