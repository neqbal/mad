package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class EventListActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper db;
    String email;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<Integer> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_event_list);

        db = new DatabaseHelper(this);
        email = getIntent().getStringExtra("email");

        listView = findViewById(R.id.listView);

        Cursor c = db.getEvents();
        while (c.moveToNext()) {
            ids.add(c.getInt(0));
            list.add(c.getString(1) + " | " + c.getString(2));
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((a, v, pos, id) -> {
            db.registerEvent(email, ids.get(pos));

            Intent i = new Intent(this, SummaryActivity.class);
            i.putExtra("email", email);
            startActivity(i);
        });
    }
}