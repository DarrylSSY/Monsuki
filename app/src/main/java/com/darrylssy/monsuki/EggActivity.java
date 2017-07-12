package com.darrylssy.monsuki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EggActivity extends AppCompatActivity
{
    //the cracked egg state
    int cracked = 0;
    DatabaseHelper myDB;
    String username = "Empty";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egg);
        myDB = new DatabaseHelper(EggActivity.this);
        username = getIntent().getStringExtra("USERNAME");
    }

    public void crackEgg(View v)
    {
        //the egg will hatch after 3 taps
        if (cracked < 3)
        {
            cracked++;
        }
        else
        {
            myDB.updateEgg(username);
            Intent baby = new Intent(this, BabyActivity.class);
            baby.putExtra("USERNAME", username);
            startActivity(baby);
        }
    }

    //go to menu
    public void clickMenu(View v)
    {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("USERNAME", username);
        startActivity(menu);
    }
}
