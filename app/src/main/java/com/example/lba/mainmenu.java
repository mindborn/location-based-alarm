package com.example.lba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
    }

    public void showdata(View v)
    {
        Intent i= new Intent(this,viewdata.class);
        startActivity(i);
    }

    public  void  addwork(View v)
    {
        Intent i=new Intent(this,home.class);
        startActivity(i);
    }
}