package com.example.lins2056.mycontactapp;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editPhone;
    EditText searchName;
    Button btnAddData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editPhone = (EditText) findViewById(R.id.editText_phonenumber);
        searchName = (EditText)findViewById(R.id.editText_searchn);
    }

    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editPhone.getText().toString());
        if(isInserted == true){
            Log.d("MyContact", "Data insertion successful");
            //create toast message to user indicating data inserted correctly
            Context conts = getApplicationContext();
            CharSequence text = "Data insertion successful";
            int duration =  Toast.LENGTH_SHORT;
            Toast.makeText(conts, text, duration).show();

        }
        else{
            Log.d("MyContact", "Data insertion unsuccessful");
            //create toast message to user indicating data inserted incorrectly
            Toast.makeText(getApplicationContext(), "Data insertion unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }

    public void viewData(View v){
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            Log.d("MyContact", "No data found in database");

            return;

        }

        StringBuffer buffer = new StringBuffer();
        //setup loop with Cursor moveToNext method
        // append each COL to the buffer
        //use getString method
        while(res.moveToNext()){
            buffer.append("ID: " + res.getString(0) + "\n");
            buffer.append("NAME: " + res.getString(1) + "\n");
            buffer.append("AGE: " + res.getString(2) + "\n");
            buffer.append("PHONE NUMBER: " + res.getString(3) + "\n\n");
        }

        Log.d("MyContact", buffer.toString());

        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true); //cancel using back button
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void search(View v){

        Cursor res = myDb.findData(searchName);
        if(res.getCount() == 0){
            showMessage("Error", "Data not found");
        }
        else {
            StringBuffer buff = new StringBuffer();
            res.moveToNext();
            buff.append("ID: " + res.getString(0) + "\n");
            buff.append("NAME: " + res.getString(1) + "\n");
            buff.append("AGE: " + res.getString(2) + "\n");
            buff.append("PHONE NUMBER: " + res.getString(3) + "\n\n");

            Log.d("MyContact", buff.toString());
            showMessage("Found Contact", buff.toString());
        }
    }

}
