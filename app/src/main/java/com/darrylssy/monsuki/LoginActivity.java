package com.darrylssy.monsuki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity
{

    private EditText username;
    private EditText password;
    DatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.editUser);
        password = (EditText) findViewById(R.id.editPass);
        myDB = new DatabaseHelper(LoginActivity.this);
    }

    //enter game
    public void clickLogin(View v)
    {
        boolean usernameFound = false;
        boolean passwordMatch = false;
        boolean eggHatched = false;
        Cursor results =  myDB.getAllData();

        //check if account details are correct
        StringBuffer buffer = new StringBuffer();
        while (results.moveToNext()) {
            String usernameInDatabase = results.getString(1).toString().toLowerCase();
            String passwordInDatabase = results.getString(3).toString();
            if (usernameInDatabase.equals(username.getText().toString().toLowerCase()))
            {

                usernameFound = true;
                if (passwordInDatabase.equals(password.getText().toString()))
                {
                    passwordMatch = true;
                    if (results.getString(4).toString().equals("Yes"))
                    {
                        eggHatched = true;
                    }
                    break;
                }
            }
        }



        if ((username.getText().length() == 0) || (password.getText().length() == 0))
        {
            Toast.makeText(getApplicationContext(), "Error: Some fields have missing data", Toast.LENGTH_LONG).show();
        }
        else if (usernameFound == false)
        {
            Toast.makeText(getApplicationContext(), "Username not found." , Toast.LENGTH_LONG).show();
        }
        else if (passwordMatch == false)
        {
            Toast.makeText(getApplicationContext(), "Password incorrect.", Toast.LENGTH_LONG).show();
        }
        else if (eggHatched == false)
        {
            Toast.makeText(getApplicationContext(), "You have logged in successfully" , Toast.LENGTH_LONG).show();
            Intent myLogin = new Intent(this, EggActivity.class);
            myLogin.putExtra("USERNAME", username.getText().toString());
            startActivity(myLogin);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "You have logged in successfully" , Toast.LENGTH_LONG).show();
            Intent myLogin = new Intent(this, BabyActivity.class);
            myLogin.putExtra("USERNAME", username.getText().toString());
            startActivity(myLogin);
        }
    }

    //go to registration screen
    public void clickRegister(View v)
    {
        Intent myRegister = new Intent(this, RegisterActivity.class);
        startActivity(myRegister);
    }

    //go to password reset screen
    public void clickForgot(View v)
    {
        Intent myForgot = new Intent(this, ForgotActivity.class);
        startActivity(myForgot);
    }
}
