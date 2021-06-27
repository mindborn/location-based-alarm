package com.example.lba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class home extends AppCompatActivity {
    EditText etlongi,etlati,etw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        etlongi=findViewById(R.id.etlongitude);
        etlati=findViewById(R.id.etlattitude);
        etw=findViewById(R.id.etdescription);


    }

    public void addalarm(View v)
    {
        if (etlongi.getText().toString().equals(""))
        {
            etlongi.setError("Enter Longitude");
        }
        else if (etlati.getText().toString().equals(""))
        {
            etlati.setError("Enter Lattitude");
        }
        else if (etw.getText().toString().equals(""))
        {
            etw.setError("Enter Alarm Description");
        }
        else
        {
                DBManager d= new DBManager(this);
                String q=d.addlocation(etlongi.getText().toString(),etlati.getText().toString(),etw.getText().toString());
                if (q.equals(""))
                {
                    Toast.makeText(this,"Data Added",Toast.LENGTH_LONG).show();
                    etlati.setText("");
                    etlongi.setText("");
                    etw.setText("");
                }
                else
                {
                    Toast.makeText(this,q,Toast.LENGTH_LONG).show();
                }
        }




    }


}