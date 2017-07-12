package com.darrylssy.monsuki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AccountActivity extends AppCompatActivity
{
    DatabaseHelper myDB;
    String username = "Empty";
    String userID = "0";
    String email = "email.com";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        myDB = new DatabaseHelper(AccountActivity.this);
        //retrieve username from previous activity
        username = getIntent().getStringExtra("USERNAME");
        //retrieving data from database that matches username
        Cursor results = myDB.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (results.moveToNext())
        {
            String usernameInDatabase = results.getString(1).toString().toLowerCase();
            if (usernameInDatabase.equals(username))
            {
                userID = Integer.toString(results.getInt(0));
                email = results.getString(2);
                break;
            }
        }
        //making a list view
        ListView accountDetails = (ListView) findViewById(R.id.accountDetails);
        HashMap<String, String> listDetails = new HashMap<>();
        listDetails.put("User ID", userID);
        listDetails.put("Username", username);
        listDetails.put("Email", email);
        List<HashMap<String, String>> listItems = new ArrayList<>();
        //list view is done with 2 text view in a new layout
        SimpleAdapter adapter = new SimpleAdapter(this, listItems, R.layout.list_items, new String[]{"First Line", "Second Line"}, new int[]{R.id.text1, R.id.text2});
        Iterator it = listDetails.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap<String, String> resultsMap = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            resultsMap.put("First Line", pair.getKey().toString());
            resultsMap.put("Second Line", pair.getValue().toString());
            listItems.add(resultsMap);
        }
        accountDetails.setAdapter(adapter);
    }

    //go to game screen
    public void clickMenu(View v)
    {
        //check if user's egg is hatched or not
        boolean eggHatched = false;
        Cursor results =  myDB.getAllData();
        StringBuffer buffer = new StringBuffer();
        while (results.moveToNext()) {
            String usernameInDatabase = results.getString(1).toString().toLowerCase();
            if (usernameInDatabase.equals(username.toLowerCase()))
            {
                if (results.getString(4).toString().equals("Yes"))
                {
                    eggHatched = true;
                }
                break;
            }
        }
        if (eggHatched == true)
        {
            Intent menu = new Intent(this, BabyActivity.class);
            menu.putExtra("USERNAME", username);
            startActivity(menu);
        }
        else
        {
            Intent menu = new Intent(this, EggActivity.class);
            menu.putExtra("USERNAME", username);
            startActivity(menu);
        }
    }
}
