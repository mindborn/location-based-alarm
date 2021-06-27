package com.example.lba;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    EditText etn, etp;
    private final int REQUEST_LOCATION_PERMISSION = 1;
    LocationHandler locationHandler;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etn = findViewById(R.id.etusername);
        etp = findViewById(R.id.etuserpass);

        DBManager d = new DBManager(this);
        locationHandler=new LocationHandler(this);

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) ==
                PackageManager.PERMISSION_GRANTED)
        {
            LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
            lm.removeUpdates(locationHandler);
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000, 10, locationHandler);
        } else
        {
            Toast.makeText(this,"Please allow location permission",Toast.LENGTH_LONG).show();
            requestPermissions(new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case 1:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
                    lm.removeUpdates(locationHandler);
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10_000, 10, locationHandler);
                } else
                {
                    Toast.makeText(this, "Location permission not granted. Application will not work correctly.", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }


    public void closeapp(View view)
    {
        finish();
    }

    public void login(View v)
    {
        if (etn.getText().toString().equals(""))
        {
            etn.setError("Enter User Name");
        } else if (etp.getText().toString().equals(""))
        {
            etp.setError("Enter Password");
        } else if (etn.getText().toString().equals("admin") && etp.getText().toString().equals("admin"))
        {
            Intent i = new Intent(this, mainmenu.class);
            startActivity(i);
            finish();
        } else
        {
            Toast.makeText(this, "Invalid User Name or Password", Toast.LENGTH_LONG).show();
            etn.setText("");
            etp.setText("");
            etn.requestFocus();
        }
    }


}