package com.example.lba;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.List;

public class LocationHandler implements LocationListener
{
    Context context;
    DBManager dbmanager;

    double threshold=1e-4;

    public LocationHandler(Context context)
    {
        this.context = context;
        dbmanager = new DBManager(context);
    }

    @Override
    public void onLocationChanged(@NonNull Location location)
    {
//        Toast.makeText(context,"Location changed "+location.getLatitude()+","+location.getLongitude(),Toast.LENGTH_LONG).show();

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        List<String []> alarms=dbmanager.getAlarms();

        for(String [] alarm: alarms)
        {
            double lng=Double.parseDouble(alarm[0]);
            double lat=Double.parseDouble(alarm[1]);

            double mh_distance=Math.abs(lng-longitude)+Math.abs(lat-latitude);
            if(mh_distance<threshold)
            {
                Intent i = new Intent(context, ReminderActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        }
    }
}
