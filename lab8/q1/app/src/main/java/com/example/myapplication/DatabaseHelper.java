package com.example.myapplication;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "TaskDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE = "tasks";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DATE = "date";
    private static final String PRIORITY = "priority";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + NAME + " TEXT,"
                + DATE + " TEXT,"
                + PRIORITY + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, task.getName());
        values.put(DATE, task.getDueDate());
        values.put(PRIORITY, task.getPriority());
        db.insert(TABLE, null, values);
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                Task t = new Task();
                t.setId(cursor.getInt(0));
                t.setName(cursor.getString(1));
                t.setDueDate(cursor.getString(2));
                t.setPriority(cursor.getString(3));
                list.add(t);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, task.getName());
        values.put(DATE, task.getDueDate());
        values.put(PRIORITY, task.getPriority());

        db.update(TABLE, values, ID + "=?",
                new String[]{String.valueOf(task.getId())});
        db.close();
    }

    public void deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE + " WHERE id=?",
                new String[]{String.valueOf(id)});

        if (cursor != null && cursor.moveToFirst()) {
            Task t = new Task();
            t.setId(cursor.getInt(0));
            t.setName(cursor.getString(1));
            t.setDueDate(cursor.getString(2));
            t.setPriority(cursor.getString(3));
            cursor.close();
            return t;
        }
        return null;
    }
}