package com.example.lins2056.mycontactapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editPhone;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editPhone = (EditText) findViewById(R.id.editText_phonenumber);
    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editPhone.getText().toString());
        if(isInserted == true){
            Log.d("MyContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly

        }
        else{
            Log.d("MyContact", "Data insertion unsuccessful");
            //create toast message to user indicating data inserted incorrectly

        }

    }
}
