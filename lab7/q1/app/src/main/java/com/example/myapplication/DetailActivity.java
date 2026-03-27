package com.example.myapplication;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setDecorFitsSystemWindows(true);

        TextView tv = new TextView(this);
        tv.setPadding(30,30,30,30);

        String pkg = getIntent().getStringExtra("package");

        try {
            PackageManager pm = getPackageManager();
            PackageInfo info = pm.getPackageInfo(pkg,
                    PackageManager.GET_PERMISSIONS);

            StringBuilder details = new StringBuilder();

            details.append("Package: ").append(pkg).append("\n\n");
            details.append("Version: ").append(info.versionName).append("\n\n");

            // Permissions
            details.append("Permissions:\n");
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    details.append(p).append("\n");
                }
            }

            // Check camera/location
            details.append("\nSpecial Access:\n");
            if (hasPermission(info, "android.permission.CAMERA"))
                details.append("Camera Access\n");

            if (hasPermission(info, "android.permission.ACCESS_FINE_LOCATION"))
                details.append("Location Access\n");

            tv.setText(details.toString());

        } catch (Exception e) {
            tv.setText("Error loading details");
        }

        setContentView(tv);
    }

    private boolean hasPermission(PackageInfo info, String perm) {
        if (info.requestedPermissions == null) return false;

        for (String p : info.requestedPermissions) {
            if (p.equals(perm)) return true;
        }
        return false;
    }
}