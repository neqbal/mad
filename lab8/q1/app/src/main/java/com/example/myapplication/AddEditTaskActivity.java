package com.example.myapplication;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    EditText etName, etDate;
    Spinner spPriority;
    Button btnSave;
    DatabaseHelper db;
    int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        spPriority = findViewById(R.id.spPriority);
        btnSave = findViewById(R.id.btnSave);

        db = new DatabaseHelper(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                new String[]{"High", "Medium", "Low"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPriority.setAdapter(adapter);

        if (getIntent().hasExtra("taskId")) {
            taskId = getIntent().getIntExtra("taskId", -1);
        }
        if (taskId != -1) {
            Task t = db.getTask(taskId);

            if (t != null) {
                etName.setText(t.getName());
                etDate.setText(t.getDueDate());

                String[] priorities = {"High", "Medium", "Low"};
                for (int i = 0; i < priorities.length; i++) {
                    if (priorities[i].equals(t.getPriority())) {
                        spPriority.setSelection(i);
                        break;
                    }
                }
            }
        }
        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String date = etDate.getText().toString();
            String priority = spPriority.getSelectedItem().toString();

            Task task = new Task(taskId, name, date, priority);

            if (taskId == -1)
                db.addTask(task);
            else
                db.updateTask(task);

            finish();
        });
    }
}