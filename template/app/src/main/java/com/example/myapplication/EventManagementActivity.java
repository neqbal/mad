package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import androidx.appcompat.app.AppCompatActivity;

public class EventManagementActivity extends AppCompatActivity {
    EditText name, date, time, location, id;
    DatabaseHelper db;
    String email;
    Spinner spinner;
    ArrayList<String> eventList = new ArrayList<>();
    ArrayList<Integer> eventIds = new ArrayList<>();
    int selectedEventId = -1;

    boolean isUserInteraction = false;
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_event_management);

        db = new DatabaseHelper(this);
        email = getIntent().getStringExtra("email");

        name = findViewById(R.id.name);
        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        location = findViewById(R.id.location);
        id = findViewById(R.id.eventId);

        date.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();

            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dp = new DatePickerDialog(this,
                    (view, y, m, d) -> {
                        String selectedDate = d + "/" + (m + 1) + "/" + y;
                        date.setText(selectedDate);
                    },
                    year, month, day);

            dp.show();
        });


        time.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();

            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);

            TimePickerDialog tp = new TimePickerDialog(this,
                    (view, h, m) -> {
                        String selectedTime = String.format("%02d:%02d", h, m);
                        time.setText(selectedTime);
                    },
                    hour, minute, true);

            tp.show();
        });

        spinner = findViewById(R.id.eventSpinner);
        loadEventsIntoSpinner();
    }
    void loadEventsIntoSpinner() {
        eventList.clear();
        eventIds.clear();

        Cursor c = db.getEvents();

        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            String dateVal = c.getString(2);

            eventIds.add(id);
            eventList.add(name + " (" + dateVal + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                eventList
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        isUserInteraction = false;
        spinner.setAdapter(adapter);

        // Handle selection
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (!isUserInteraction) {
                    isUserInteraction = true; // ignore first auto trigger
                    return;
                }

                selectedEventId = eventIds.get(position);

                if (selectedEventId == -1) {
                    clearFields();
                    return;
                }

                fillEventDetails(selectedEventId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    void fillEventDetails(int eventId) {
        Cursor c = db.getReadableDatabase().rawQuery(
                "SELECT * FROM events WHERE id=?",
                new String[]{String.valueOf(eventId)}
        );

        if (c.moveToFirst()) {
            id.setText(String.valueOf(c.getInt(0)));
            name.setText(c.getString(1));
            date.setText(c.getString(2));
            time.setText(c.getString(3));
            location.setText(c.getString(4));
        }
    }

    void clearFields() {
        id.setText("");
        name.setText("");
        date.setText("");
        time.setText("");
        location.setText("");

        selectedEventId = -1;

        spinner.setSelection(0);
    }

    public void addEvent(View v) {

        if (id.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter Event ID", Toast.LENGTH_SHORT).show();
            return;
        }

        int eventId = Integer.parseInt(id.getText().toString());

        if (db.eventIdExists(eventId)) {
            Toast.makeText(this, "Event ID already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        db.insertEvent(
                eventId,
                name.getText().toString(),
                date.getText().toString(),
                time.getText().toString(),
                location.getText().toString()
        );

        Toast.makeText(this, "Event added", Toast.LENGTH_SHORT).show();

        clearFields();
        loadEventsIntoSpinner();
    }

    public void updateEvent(View v) {
        if (selectedEventId != -1) {
            db.updateEvent(
                    selectedEventId,
                    name.getText().toString(),
                    date.getText().toString(),
                    time.getText().toString(),
                    location.getText().toString()
            );
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

            loadEventsIntoSpinner(); // refresh list
        }
    }

    public void goNext(View v) {
        Intent i = new Intent(this, EventListActivity.class);
        i.putExtra("email", email);
        startActivity(i);
    }
}
