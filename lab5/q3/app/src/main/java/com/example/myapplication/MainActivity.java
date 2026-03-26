package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner movie, theatre;
    DatePicker datePicker;
    TimePicker timePicker;
    ToggleButton toggle;
    Button book, reset;

    String[] movies = {"Avengers", "Inception", "Interstellar"};
    String[] theatres = {"PVR", "INOX", "Cinepolis"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        movie = findViewById(R.id.movieSpinner);
        theatre = findViewById(R.id.theatreSpinner);
        datePicker = findViewById(R.id.datePicker);
        timePicker = findViewById(R.id.timePicker);
        toggle = findViewById(R.id.toggleTicket);
        book = findViewById(R.id.bookBtn);
        reset = findViewById(R.id.resetBtn);

        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, movies);
        movie.setAdapter(mAdapter);

        ArrayAdapter<String> tAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, theatres);
        theatre.setAdapter(tAdapter);

        // Handle Premium restriction
        toggle.setOnCheckedChangeListener((btn, isChecked) -> checkPremiumTime());

        // Also check when time changes
        timePicker.setOnTimeChangedListener((view, hour, minute) -> checkPremiumTime());

        book.setOnClickListener(v -> {

            if (!validateInputs()) return;

            String mov = movie.getSelectedItem().toString();
            String th = theatre.getSelectedItem().toString();

            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();

            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            String time = hour + ":" + minute;
            String date = day + "/" + month + "/" + year;

            String type = toggle.isChecked() ? "Premium" : "Standard";

            int seats = (int)(Math.random() * 50 + 1);

            Intent i = new Intent(MainActivity.this, ResultActivity.class);
            i.putExtra("movie", mov);
            i.putExtra("theatre", th);
            i.putExtra("date", date);
            i.putExtra("time", time);
            i.putExtra("type", type);
            i.putExtra("seats", seats);

            startActivity(i);
        });

        reset.setOnClickListener(v -> resetFields());
    }

    private void checkPremiumTime() {
        if (toggle.isChecked()) {
            int hour = timePicker.getHour();
            book.setEnabled(hour >= 12);
        } else {
            book.setEnabled(true);
        }
    }

    private boolean validateInputs() {

        if (movie.getSelectedItem() == null ||
                theatre.getSelectedItem() == null) {

            Toast.makeText(this, "Select all fields", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void resetFields() {

        movie.setSelection(0);
        theatre.setSelection(0);
        toggle.setChecked(false);

        Calendar c = Calendar.getInstance();
        datePicker.updateDate(
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );

        timePicker.setHour(c.get(Calendar.HOUR_OF_DAY));
        timePicker.setMinute(c.get(Calendar.MINUTE));

        book.setEnabled(true);
    }
}