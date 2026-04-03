package com.example.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    EditText etName, etYear;
    Spinner spRating;
    Button btnSave;
    ListView listView;

    TextView tvName, tvYear, tvRating;

    DBHelper db;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etYear = findViewById(R.id.etYear);
        spRating = findViewById(R.id.spRating);
        btnSave = findViewById(R.id.btnSave);
        listView = findViewById(R.id.listView);

        tvName = findViewById(R.id.tvName);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);

        db = new DBHelper(this);

        // Spinner values (1-5)
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new Integer[]{1,2,3,4,5}
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRating.setAdapter(adapter);

        loadMovies();

        // Save movie
        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String year = etYear.getText().toString();
            int rating = Integer.parseInt(spRating.getSelectedItem().toString());

            if (name.isEmpty() || year.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            db.addMovie(name, year, rating);
            loadMovies();

            etName.setText("");
            etYear.setText("");
        });

        // List click → show details
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Movie m = movieList.get(position);

            tvName.setText(m.getName());
            tvYear.setText(m.getYear());
            tvRating.setText(String.valueOf(m.getRating()));
        });
    }

    private void loadMovies() {
        movieList = db.getAllMovies();

        List<String> names = new ArrayList<>();
        for (Movie m : movieList) {
            names.add(m.getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                names
        );

        listView.setAdapter(adapter);
    }
}