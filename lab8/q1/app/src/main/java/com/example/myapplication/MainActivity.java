package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    DatabaseHelper db;
    List<Task> taskList;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.btnAdd);
        db = new DatabaseHelper(this);

        loadTasks();

        btnAdd.setOnClickListener(v -> {
            startActivity(new Intent(this, AddEditTaskActivity.class));
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Task t = taskList.get(position);
            Intent i = new Intent(this, AddEditTaskActivity.class);
            i.putExtra("taskId", t.getId());
            startActivity(i);
        });
    }

    private void loadTasks() {
        taskList = db.getAllTasks();
        adapter = new TaskAdapter(this, taskList);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }
}