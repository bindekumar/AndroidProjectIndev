package com.indev.ari_tracker.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.indev.ari_tracker.Database.SharedPrefHelper;
import com.indev.ari_tracker.Database.SqliteHelper;
import com.indev.ari_tracker.R;
import com.indev.ari_tracker.Util.DataDownload;

public class SplashScreen extends AppCompatActivity {

    SharedPrefHelper sharedPrefHelper=null;
    private static String splashLoaded = "No";
    final int REQUEST = 112, SPLASH_DISPLAY_LENGTH = 3000;  //2 seconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.appcolor));
        sharedPrefHelper = new SharedPrefHelper(this);

        Log.d("TAG", "@@@ IN IF Build.VERSION.SDK_INT >= 23");
        String[] PERMISSIONS = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        };
        if (!hasPermissions(this, PERMISSIONS)) {
            Log.d("TAG", "@@@ IN IF hasPermissions");
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST);

        } else {
            Log.d("TAG", "@@@ IN ELSE hasPermissions");
            callNextActivity();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    callNextActivity();
                } else {
                    Log.d("TAG", "@@@ PERMISSIONS Denied");
                    Toast.makeText(this, "PERMISSIONS_Denied", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", getPackageName(), null);
                    intent.setData(uri);
                    startActivity(intent);
                    finish();
                }
            }
        }
    }
    private void callNextActivity() {
        new Handler().postDelayed(() -> {
        //    startActivity(new Intent(getApplicationContext(),LoginActivity.class));

            splashLoaded = SharedPrefHelper.getString("isSplashLoaded", "No");

            if (splashLoaded.equals("No")) {
                final SqliteHelper sqliteHelper = new SqliteHelper(getApplicationContext());
                sqliteHelper.openDataBase();
                new DataDownload(sharedPrefHelper,getApplicationContext(), sqliteHelper).execute();
            } else if (SharedPrefHelper.getString("login_check", "").equals("yes")){
                startActivity(new Intent(getApplicationContext(), Main_Menu.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null){
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
