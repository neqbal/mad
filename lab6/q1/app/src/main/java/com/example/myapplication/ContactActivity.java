package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView tv = new TextView(this);
        tv.setText("Contact Us\n\nPhone: 9876543210\nEmail: xyzfitness@gmail.com");
        tv.setPadding(30,30,30,30);

        setContentView(tv);
    }
}