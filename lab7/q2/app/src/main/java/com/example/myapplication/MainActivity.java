package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView menuIcon, displayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        menuIcon = findViewById(R.id.menuIcon);
        displayImage = findViewById(R.id.displayImage);

        menuIcon.setOnClickListener(v -> {

            PopupMenu popup = new PopupMenu(this, menuIcon);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

            popup.setOnMenuItemClickListener(item -> {

                if (item.getItemId() == R.id.img1) {
                    displayImage.setImageResource(R.drawable.img1);
                    showToast("Image 1 Selected", R.drawable.img1);
                }
                else if (item.getItemId() == R.id.img2) {
                    displayImage.setImageResource(R.drawable.img2);
                    showToast("Image 2 Selected", R.drawable.img2);
                }

                return true;
            });

            popup.show();
        });
    }

    private void showToast(String message, int imageRes) {

        ImageView img = new ImageView(this);
        img.setImageResource(imageRes);

        Toast toast = new Toast(this);
        toast.setView(img);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}