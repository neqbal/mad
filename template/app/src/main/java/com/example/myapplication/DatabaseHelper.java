package com.example.myapplication;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "app.db";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT)");

        db.execSQL("CREATE TABLE events(" +
                "id INTEGER PRIMARY KEY UNIQUE," +
                "name TEXT, date TEXT, time TEXT, location TEXT)");

        db.execSQL("CREATE TABLE registrations(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "email TEXT, event_id INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    // USER
    public boolean registerUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("password", password);
        long res = db.insert("users", null, cv);
        return res != -1;
    }

    public boolean loginUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE email=? AND password=?",
                new String[]{email, password});
        return c.getCount() > 0;
    }

    // EVENTS
    public boolean insertEvent(int id, String name, String date, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("location", location);

        return db.insert("events", null, cv) != -1;
    }

    public Cursor getEvents() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM events", null);
    }

    public boolean updateEvent(int id, String name, String date, String time, String location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("location", location);
        return db.update("events", cv, "id=?", new String[]{String.valueOf(id)}) > 0;
    }

    // REGISTRATION
    public boolean registerEvent(String email, int eventId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", email);
        cv.put("event_id", eventId);
        return db.insert("registrations", null, cv) != -1;
    }

    public Cursor getUserRegistrations(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT users.email, events.name, events.date, events.time, events.location " +
                        "FROM registrations " +
                        "JOIN events ON registrations.event_id = events.id " +
                        "JOIN users ON users.email = registrations.email " +
                        "WHERE users.email=?",
                new String[]{email});
    }

    public boolean eventIdExists(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM events WHERE id=?",
                new String[]{String.valueOf(id)}
        );

        return c.getCount() > 0;
    }
}