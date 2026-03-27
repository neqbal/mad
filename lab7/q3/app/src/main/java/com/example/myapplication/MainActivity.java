package com.example.myapplication;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView contentText;

    String content = "Digital transformation is the integration of digital technology into all areas of a business. "
            + "It fundamentally changes how organizations operate and deliver value to customers. "
            + "It also involves cultural change and continuous innovation.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        contentText = findViewById(R.id.contentText);
        contentText.setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.search) {
            searchKeyword();
        }
        else if (item.getItemId() == R.id.highlight) {
            highlightText();
        }
        else if (item.getItemId() == R.id.sort) {
            sortContent();
        }

        return true;
    }

    // 🔍 Search
    private void searchKeyword() {
        EditText input = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Enter Keyword")
                .setView(input)
                .setPositiveButton("Search", (d, w) -> {

                    String keyword = input.getText().toString();

                    if (content.contains(keyword)) {
                        contentText.setText("Found: " + keyword);
                    } else {
                        contentText.setText("Not Found");
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // 🎨 Highlight
    private void highlightText() {
        EditText input = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Enter word to highlight")
                .setView(input)
                .setPositiveButton("Highlight", (d, w) -> {

                    String word = input.getText().toString();
                    SpannableString spannable = new SpannableString(content);

                    int start = content.indexOf(word);

                    if (start >= 0) {
                        spannable.setSpan(
                                new BackgroundColorSpan(android.graphics.Color.YELLOW),
                                start,
                                start + word.length(),
                                0
                        );
                    }

                    contentText.setText(spannable);
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // 🔤 Sort
    private void sortContent() {

        String[] words = content.split(" ");

        Arrays.sort(words);

        String sorted = String.join(" ", words);

        contentText.setText(sorted);
    }
}