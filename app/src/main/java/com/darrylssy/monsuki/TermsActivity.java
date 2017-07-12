package com.darrylssy.monsuki;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TermsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
    }

    //return to register screen
    public void clickBack(View v)
    {
        Intent back = new Intent(this, RegisterActivity.class);
        startActivity(back);
    }
}
