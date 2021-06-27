package com.example.lba;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper
{
    public DBManager(@Nullable Context context)
    {
        super(context, "lbadb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
            db.execSQL("create table if not exists locat(longitude VARCHAR ,lattitude VARCHAR,descr VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
                db.execSQL("drop table if exists locat");
                onCreate(db);
    }

    public List<String> retrievedata()
    {
        SQLiteDatabase sd=getReadableDatabase();
        Cursor c=sd.query("locat",null,null,null,null,null,null);
        List<String> list= new ArrayList<>();
        while (c.moveToNext())
        {
            list.add(c.getString(0));
        }
        c.close();
        sd.close();
        return list;
    }

    public List<String[]> getAlarms()
    {
        SQLiteDatabase sd=getReadableDatabase();
        Cursor c=sd.query("locat",null,null,null,null,null,null);
        List<String[]> list= new ArrayList<>();
        while (c.moveToNext())
        {
            list.add(new String[]{c.getString(0),c.getString(1),c.getString(2)});
        }
        c.close();
        sd.close();
        return list;
    }



    public  String addlocation(String longi,String lati, String desc)
    {
        SQLiteDatabase db= getWritableDatabase();
        try
        {
            db.execSQL("insert into locat(longitude,lattitude,descr)values('" + longi + "','" + lati + "','" + desc + "')");
            db.close();
            return ("");
        }
        catch (Exception e1)
        {

            return e1.getMessage();
        }
    }
}
