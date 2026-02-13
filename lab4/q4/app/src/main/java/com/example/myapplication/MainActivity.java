package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox pizza, burger, pasta, sandwich, coffee;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        pizza = findViewById(R.id.pizza);
        burger = findViewById(R.id.burger);
        pasta = findViewById(R.id.pasta);
        sandwich = findViewById(R.id.sandwich);
        coffee = findViewById(R.id.coffee);
        submit = findViewById(R.id.submitBtn);

        submit.setOnClickListener(v -> {

            StringBuilder order = new StringBuilder();
            int total = 0;

            if (pizza.isChecked()) { order.append("Pizza - ₹150\n"); total += 150; }
            if (burger.isChecked()) { order.append("Burger - ₹80\n"); total += 80; }
            if (pasta.isChecked()) { order.append("Pasta - ₹120\n"); total += 120; }
            if (sandwich.isChecked()) { order.append("Sandwich - ₹70\n"); total += 70; }
            if (coffee.isChecked()) { order.append("Coffee - ₹50\n"); total += 50; }

            order.append("\nTotal = ₹").append(total);

            // Lock checkboxes
            pizza.setEnabled(false);
            burger.setEnabled(false);
            pasta.setEnabled(false);
            sandwich.setEnabled(false);
            coffee.setEnabled(false);
            submit.setEnabled(false);

            Intent i = new Intent(MainActivity.this, ResultActivity.class);
            i.putExtra("ORDER", order.toString());
            startActivity(i);
        });
    }
}