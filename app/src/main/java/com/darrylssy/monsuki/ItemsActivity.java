package com.darrylssy.monsuki;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsActivity extends AppCompatActivity
{

    private String selectedCategory;
    private ListView listOfItems;
    private TextView header;
    private ArrayList<String> listClothes = new ArrayList<String>();
    private ArrayList<String> listFood = new ArrayList<String>();
    private ArrayList<String> listItems = new ArrayList<String>();
    private ArrayList<String> listShop = new ArrayList<String>();
    DatabaseHelper myDB;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        selectedCategory=b.getString("category");
        listOfItems = (ListView)findViewById(R.id.listOfItems);
        myDB = new DatabaseHelper(ItemsActivity.this);
        username = getIntent().getStringExtra("USERNAME");
        //set header to the category
        header = (TextView)findViewById(R.id.header);
        header.setText(selectedCategory);
        String[]clothes = new String[]{"Black cap", "Striped tee", "Shades", "Rainbow cap"};
        String[]food = new String[]{"Chicken wings", "Steak", "Nuggets", "Fries", "Biscuit", "Tuna"};
        String[]items = new String[]{"Beach ball", "Wood branch", "Rubber duck"};
        String[]shop = new String[]{"Special treat", "Sparkling shirt", "Diamond cap", "Dog bone", "Sundae", "Bubble gum", "Umbrella"};
        for(int i = 0; i < clothes.length; i++)
        {
            listClothes.add(clothes[i]);
        }
        for(int i = 0; i < food.length; i++)
        {
            listFood.add(food[i]);
        }
        for(int i = 0; i < items.length; i++)
        {
            listItems.add(items[i]);
        }
        for(int i = 0; i < shop.length; i++)
        {
            listShop.add(shop[i]);
        }
        if(selectedCategory.equals("Clothes"))
        {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listClothes);
            listOfItems.setAdapter(adapter);
        }
        else if(selectedCategory.equals("Food"))
        {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listFood);
            listOfItems.setAdapter(adapter);
        }
        else if(selectedCategory.equals("Items"))
        {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItems);
            listOfItems.setAdapter(adapter);
        }
        else if(selectedCategory.equals("Shop"))
        {
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listShop);
            listOfItems.setAdapter(adapter);
        }
    }

    //return back to game
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
}
