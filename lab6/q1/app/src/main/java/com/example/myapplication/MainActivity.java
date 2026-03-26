package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.workout) {
            startActivity(new Intent(this, WorkoutActivity.class));
        }
        else if (id == R.id.trainers) {
            startActivity(new Intent(this, TrainersActivity.class));
        }
        else if (id == R.id.membership) {
            startActivity(new Intent(this, MembershipActivity.class));
        }
        else if (id == R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
        }
        else if (id == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        else if (id == R.id.contact) {
            startActivity(new Intent(this, ContactActivity.class));
        }

        return true;
    }
}