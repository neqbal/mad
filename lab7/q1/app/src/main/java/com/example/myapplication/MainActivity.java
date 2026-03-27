package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<ApplicationInfo> appList;
    PackageManager pm;
    ArrayList<String> names = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.appList);
        pm = getPackageManager();

        appList = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo app : appList) {
            names.add(pm.getApplicationLabel(app).toString());
        }

        listView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, names));

        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            ApplicationInfo app = appList.get(position);
            showOptions(app);
            return true;
        });
    }

    private void showOptions(ApplicationInfo app) {

        boolean isSystem = (app.flags & ApplicationInfo.FLAG_SYSTEM) != 0;

        String[] options = {
                "Type: " + (isSystem ? "System App" : "User App"),
                "Open App",
                "Uninstall",
                "View Details"
        };

        new AlertDialog.Builder(this)
                .setTitle(pm.getApplicationLabel(app))
                .setItems(options, (dialog, which) -> {

                    switch (which) {

                        case 1: // Open
                            Intent launch = pm.getLaunchIntentForPackage(app.packageName);
                            if (launch != null)
                                startActivity(launch);
                            break;

                        case 2: // Uninstall
                            confirmUninstall(app.packageName);
                            break;

                        case 3: // Details
                            Intent i = new Intent(this, DetailActivity.class);
                            i.putExtra("package", app.packageName);
                            startActivity(i);
                            break;
                    }
                })
                .show();
    }

    private void confirmUninstall(String packageName) {

        new AlertDialog.Builder(this)
                .setTitle("Confirm Uninstall")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes", (d, w) -> {
                    Intent intent = new Intent(Intent.ACTION_DELETE);
                    intent.setData(Uri.parse("package:" + packageName));
                    startActivity(intent);
                })
                .setNegativeButton("No", null)
                .show();
    }
}