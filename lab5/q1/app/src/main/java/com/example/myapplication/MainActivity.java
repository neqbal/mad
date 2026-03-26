package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    EditText vehicleNo, rcNo;
    Button submit;

    String[] vehicleTypes = {"Car", "Bike", "Truck", "Bus"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.vehicleType);
        vehicleNo = findViewById(R.id.vehicleNumber);
        rcNo = findViewById(R.id.rcNumber);
        submit = findViewById(R.id.submitBtn);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                vehicleTypes
        );
        spinner.setAdapter(adapter);

        submit.setOnClickListener(v -> {

            String type = spinner.getSelectedItem().toString();
            String vNo = vehicleNo.getText().toString();
            String rc = rcNo.getText().toString();

            Intent i = new Intent(MainActivity.this, ConfirmActivity.class);
            i.putExtra("type", type);
            i.putExtra("vehicleNo", vNo);
            i.putExtra("rcNo", rc);
            startActivity(i);
        });
    }
}