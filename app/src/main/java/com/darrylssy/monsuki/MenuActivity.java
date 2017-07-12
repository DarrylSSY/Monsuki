package com.darrylssy.monsuki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity
{
    DatabaseHelper myDB;

    private ListView listOfCategories;
    private ArrayList<String> listItems = new ArrayList<String>();
    String username ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        username = getIntent().getStringExtra("USERNAME");
        listOfCategories = (ListView) findViewById(R.id.listOfCategories);
        myDB = new DatabaseHelper(MenuActivity.this);
        String[] values = new String[] {"Clothes", "Food", "Items", "Shop"};
        for(int i = 0; i < values.length; i++)
        {
            listItems.add(values[i]);
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        listOfCategories.setAdapter(adapter);
        listOfCategories.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?>parent, View view, int position, long id)
            {
                String selectedCategory = listItems.get(position).toString();

                Intent itemIntent = new Intent(view.getContext(),ItemsActivity.class);

                itemIntent.putExtra("USERNAME", username);
                itemIntent.putExtra("category", selectedCategory);


                startActivity(itemIntent);
            }
        });
    }

    //go back
    public void clickMenu(View v)
    {
        boolean eggHatched = false;
        Cursor results =  myDB.getAllData();

        StringBuffer buffer = new StringBuffer();
        while (results.moveToNext())
        {

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


    //logout of game
    public void clickLogout(View v)
    {
        Intent logout = new Intent(this, LoginActivity.class);
        startActivity(logout);
    }

    public void clickAccount(View v)
    {
        Intent account = new Intent(this, AccountActivity.class);
        account.putExtra("USERNAME", username);
        startActivity(account);
    }
}
