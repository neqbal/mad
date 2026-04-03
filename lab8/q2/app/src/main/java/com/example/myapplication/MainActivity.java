package com.example.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText etName, etCost;
    Button btnAdd, btnAddToCart, btnTotal;
    Spinner spinner;
    TextView tvTotal;

    DBHelper db;
    List<GroceryItem> itemList;
    List<GroceryItem> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etCost = findViewById(R.id.etCost);
        btnAdd = findViewById(R.id.btnAdd);
        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnTotal = findViewById(R.id.btnTotal);
        spinner = findViewById(R.id.spinner);
        tvTotal = findViewById(R.id.tvTotal);

        db = new DBHelper(this);

        loadSpinner();

        // Add item to DB
        btnAdd.setOnClickListener(v -> {
            String name = etName.getText().toString();
            double cost = Double.parseDouble(etCost.getText().toString());

            db.addItem(name, cost);
            loadSpinner();

            etName.setText("");
            etCost.setText("");
        });

        // Add selected item to cart
        btnAddToCart.setOnClickListener(v -> {
            GroceryItem item = itemList.get(spinner.getSelectedItemPosition());
            selectedItems.add(item);
            Toast.makeText(this, item.getName() + " added", Toast.LENGTH_SHORT).show();
        });

        // Calculate total
        btnTotal.setOnClickListener(v -> {
            double total = 0;
            for (GroceryItem item : selectedItems) {
                total += item.getCost();
            }
            tvTotal.setText("Total: ₹ " + total);
        });
    }

    private void loadSpinner() {
        itemList = db.getAllItems();

        List<String> names = new ArrayList<>();
        for (GroceryItem item : itemList) {
            names.add(item.getName() + " (₹" + item.getCost() + ")");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                names
        );

        spinner.setAdapter(adapter);
    }
}