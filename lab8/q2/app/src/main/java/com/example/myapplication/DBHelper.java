package com.example.myapplication;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import java.util.*;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "GroceryDB";
    private static final int DB_VERSION = 1;

    private static final String TABLE = "items";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, cost REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addItem(String name, double cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("cost", cost);
        db.insert(TABLE, null, cv);
        db.close();
    }

    public List<GroceryItem> getAllItems() {
        List<GroceryItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM items", null);

        if (c.moveToFirst()) {
            do {
                GroceryItem item = new GroceryItem(
                        c.getInt(0),
                        c.getString(1),
                        c.getDouble(2)
                );
                list.add(item);
            } while (c.moveToNext());
        }
        c.close();
        return list;
    }
}