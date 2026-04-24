package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;

import java.util.*;

public class TaskAdapter extends ArrayAdapter<Task> {

    Context context;
    List<Task> list;
    DatabaseHelper db;

    public TaskAdapter(Context context, List<Task> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task t = list.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_task, parent, false);
        }

        TextView tv = convertView.findViewById(R.id.tvTask);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        tv.setText(t.getName() + "\n" +
                t.getDueDate() + " | " + t.getPriority());

        // ✅ EDIT BUTTON
        btnEdit.setOnClickListener(v -> {
            Intent i = new Intent(context, AddEditTaskActivity.class);
            i.putExtra("taskId", t.getId());
            context.startActivity(i);
        });

        // ✅ DELETE BUTTON
        btnDelete.setOnClickListener(v -> {
            db.deleteTask(t.getId());
            list.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}