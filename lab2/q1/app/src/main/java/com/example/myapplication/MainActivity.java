package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView stateText;

    private void updateState(String state){
        stateText.append(state + "\n");
        Log.d(TAG, state);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateText = findViewById(R.id.stateText);
        Button btn = findViewById(R.id.btnNext);

        btn.setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });

        updateState("onCreate() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateState("onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateState("onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        updateState("onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateState("onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}