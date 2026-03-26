package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_confirm);

        TextView details = findViewById(R.id.detailsText);
        Button confirm = findViewById(R.id.confirmBtn);
        Button edit = findViewById(R.id.editBtn);

        String type = getIntent().getStringExtra("type");
        String vNo = getIntent().getStringExtra("vehicleNo");
        String rc = getIntent().getStringExtra("rcNo");

        String info = "Vehicle Type: " + type +
                "\nVehicle No: " + vNo +
                "\nRC No: " + rc;

        details.setText(info);

        confirm.setOnClickListener(v -> {
            int serial = new Random().nextInt(10000);
            Toast.makeText(this,
                    "Parking Confirmed! Serial No: " + serial,
                    Toast.LENGTH_LONG).show();
        });

        edit.setOnClickListener(v -> finish());
    }
}