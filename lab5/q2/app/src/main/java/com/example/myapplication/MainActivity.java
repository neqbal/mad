package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner source, destination;
    DatePicker datePicker;
    ToggleButton toggle;
    Button submit, reset;

    String[] cities = {"Mangalore", "Udupi", "Bangalore", "Mumbai", "Delhi"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        source = findViewById(R.id.sourceSpinner);
        destination = findViewById(R.id.destinationSpinner);
        datePicker = findViewById(R.id.datePicker);
        toggle = findViewById(R.id.toggleTrip);
        submit = findViewById(R.id.submitBtn);
        reset = findViewById(R.id.resetBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                cities
        );

        source.setAdapter(adapter);
        destination.setAdapter(adapter);

        submit.setOnClickListener(v -> {

            String src = source.getSelectedItem().toString();
            String dest = destination.getSelectedItem().toString();

            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();

            String tripType = toggle.isChecked() ? "Round Trip" : "One Way";

            String date = day + "/" + month + "/" + year;

            Intent i = new Intent(MainActivity.this, ResultActivity.class);
            i.putExtra("src", src);
            i.putExtra("dest", dest);
            i.putExtra("date", date);
            i.putExtra("trip", tripType);

            startActivity(i);
        });

        reset.setOnClickListener(v -> resetFields());
    }

    private void resetFields() {

        source.setSelection(0);
        destination.setSelection(0);
        toggle.setChecked(false);

        Calendar calendar = Calendar.getInstance();
        datePicker.updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
    }
}