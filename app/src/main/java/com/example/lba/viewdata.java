package com.example.lba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class viewdata extends AppCompatActivity
{

    private List<String> list;
    ArrayAdapter<String> adapter;
    ListView lvd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        DBManager d = new DBManager(this);
        list = d.retrievedata();

        lvd = findViewById(R.id.lvdata);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lvd.setAdapter(adapter);


    }
}