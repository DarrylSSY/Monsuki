package com.darrylssy.monsuki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BabyActivity extends AppCompatActivity
{
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baby);
        username = getIntent().getStringExtra("USERNAME");
    }

    //go to menu
    public void clickMenu(View v)
    {
        Intent menu = new Intent(this, MenuActivity.class);
        menu.putExtra("USERNAME", username);
        startActivity(menu);

    }
}
