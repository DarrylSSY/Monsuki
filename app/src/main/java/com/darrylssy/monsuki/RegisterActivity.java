package com.darrylssy.monsuki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity
{

    private EditText username, password, confirmpass, email;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (EditText) findViewById(R.id.editUser);
        password = (EditText) findViewById(R.id.editPass);
        confirmpass = (EditText) findViewById(R.id.editConfirm);
        email = (EditText) findViewById(R.id.editEmail);
        myDB = new DatabaseHelper(RegisterActivity.this);
    }

    public void clickRegister(View v)
    {
        if ((username.getText().length() == 0) || (password.getText().length() == 0) || (confirmpass.getText().length() ==0) || (email.getText().length() ==0))
        {
            Toast.makeText(getApplicationContext(), "Error: Some fields have missing data", Toast.LENGTH_LONG).show();
        }
        else if (password.getText() == confirmpass.getText())
        {
            Toast.makeText(getApplicationContext(), "Error: Password data is incorrect", Toast.LENGTH_LONG).show();
        }
        else
        {
            boolean isInserted = myDB.insertData(username.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString());
            Toast.makeText(getApplicationContext(), "You have signed up successfully", Toast.LENGTH_LONG).show();
            Intent mySignup = new Intent(this, LoginActivity.class);
            startActivity(mySignup);
        }
    }

    //return to login screen
    public void clickBack(View v)
    {
        Intent back = new Intent(this, LoginActivity.class);
        startActivity(back);
    }

    //view terms of conditions
    public void clickTerms(View v)
    {
        Intent terms = new Intent(this, TermsActivity.class);
        startActivity(terms);
    }

}
