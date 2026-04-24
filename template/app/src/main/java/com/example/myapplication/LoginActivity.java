package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText email, password;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        db = new DatabaseHelper(this);
    }

    public void register(View v) {
        if (db.registerUser(email.getText().toString(), password.getText().toString()))
            Toast.makeText(this, "Registered", Toast.LENGTH_SHORT).show();
    }

    public void login(View v) {
        if (db.loginUser(email.getText().toString(), password.getText().toString())) {
            Intent i = new Intent(this, EventManagementActivity.class);
            i.putExtra("email", email.getText().toString());
            startActivity(i);
        } else {
            Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show();
        }
    }
}