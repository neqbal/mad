package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Prevent content from hiding under notification panel
        getWindow().setDecorFitsSystemWindows(true);

        setContentView(R.layout.activity_main);

        button = findViewById(R.id.btnClick);
        toggleButton = findViewById(R.id.toggleButton);

        button.setOnClickListener(v ->
                showCustomToast("Button Clicked!", R.drawable.success)
        );

        toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                showCustomToast("Toggle ON", R.drawable.toggle);
            } else {
                showCustomToast("Toggle OFF", R.drawable.toggle);
            }
        });
    }

    private void showCustomToast(String message, int imageRes) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        ImageView image = layout.findViewById(R.id.toastImage);
        TextView text = layout.findViewById(R.id.toastText);

        image.setImageResource(imageRes);
        text.setText(message);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}