package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {

    TextView tv;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_summary);

        tv = findViewById(R.id.textView);
        db = new DatabaseHelper(this);

        String email = getIntent().getStringExtra("email");

        Cursor c = db.getUserRegistrations(email);

        StringBuilder sb = new StringBuilder();
        sb.append("Email: ").append(email).append("\n\n");

        while (c.moveToNext()) {
            sb.append("Event: ").append(c.getString(1)).append("\n");
            sb.append("Date: ").append(c.getString(2)).append("\n");
            sb.append("Time: ").append(c.getString(3)).append("\n");
            sb.append("Location: ").append(c.getString(4)).append("\n\n");
        }

        tv.setText(sb.toString());
    }
}