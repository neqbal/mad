package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean isLinear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLayout();
    }

    private void loadLayout() {
        if (isLinear) {
            setContentView(R.layout.activity_linear);
        } else {
            setContentView(R.layout.activity_relative);
        }

        Button btnSwitch = findViewById(R.id.btnSwitch);
        btnSwitch.setOnClickListener(v -> {
            isLinear = !isLinear;
            loadLayout();
        });
    }
}
