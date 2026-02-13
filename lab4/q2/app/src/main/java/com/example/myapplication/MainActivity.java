package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button cupcake = findViewById(R.id.btnCupcake);
        Button lollipop = findViewById(R.id.btnLollipop);
        Button marshmallow = findViewById(R.id.btnMarshmallow);
        Button oreo = findViewById(R.id.btnOreo);
        Button pie = findViewById(R.id.btnPie);

        cupcake.setOnClickListener(v ->
                showToast("Cupcake", R.drawable.cupcake));

        lollipop.setOnClickListener(v ->
                showToast("Lollipop", R.drawable.lollipop));

        marshmallow.setOnClickListener(v ->
                showToast("Marshmallow", R.drawable.marshmallow));

        oreo.setOnClickListener(v ->
                showToast("Oreo", R.drawable.oreo));

        pie.setOnClickListener(v ->
                showToast("Pie", R.drawable.pie));
    }

    private void showToast(String versionName, int iconRes) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, null);

        ImageView icon = layout.findViewById(R.id.toastIcon);
        TextView text = layout.findViewById(R.id.toastText);

        icon.setImageResource(iconRes);
        text.setText("Android Version: " + versionName);

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}