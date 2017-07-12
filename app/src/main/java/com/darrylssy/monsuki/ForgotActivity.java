package com.darrylssy.monsuki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotActivity extends AppCompatActivity
{

    private EditText email;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        myDB = new DatabaseHelper(ForgotActivity.this);
        email = (EditText) findViewById(R.id.editEmail);


    }

    //reset button
    public void clickReset(View v)
    {
        //check if email is in database
        boolean emailFound = false;
        Cursor results =  myDB.getAllData();

        StringBuffer buffer = new StringBuffer();
        while (results.moveToNext()) {

            String emailInDatabase = results.getString(2).toString().toLowerCase();

            if (emailInDatabase.equals(email.getText().toString().toLowerCase()))
            {

                emailFound = true;
                break;
            }

        }


        if (emailFound == false)
        {
            Toast.makeText(getApplicationContext(),"Email not found on database", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Reset sent to email", Toast.LENGTH_LONG).show();
            Intent myReset = new Intent(this, LoginActivity.class);
            startActivity(myReset);
        }
    }

    //return back to login
    public void clickBack(View v)
    {
        Intent myBack = new Intent(this, LoginActivity.class);
        startActivity(myBack);
    }

}
