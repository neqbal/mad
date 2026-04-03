package com.example.myapplication;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "MovieDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movies(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, year TEXT, rating INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addMovie(String name, String year, int rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("year", year);
        cv.put("rating", rating);
        db.insert("movies", null, cv);
        db.close();
    }

    public List<Movie> getAllMovies() {
        List<Movie> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM movies", null);

        if (c.moveToFirst()) {
            do {
                list.add(new Movie(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3)
                ));
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
}