package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MembershipActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);

        TextView tv = new TextView(this);
        tv.setText("Membership Packages:\n\nBasic - ₹1000/month\nPremium - ₹2000/month\nAnnual - ₹10000/year");
        tv.setPadding(30,30,30,30);

        setContentView(tv);
    }
}