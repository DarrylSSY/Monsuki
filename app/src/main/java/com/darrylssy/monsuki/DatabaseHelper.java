package com.darrylssy.monsuki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ssyda on 2017-07-12.
 */

public class DatabaseHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="monsuki.db";

    public static final String ACCOUNTS="accounts_table";
    public static final String ID="ID";
    public static final String USERNAME="Username";
    public static final String EMAIIL="Email";
    public static final String PASSWORD="Password";
    public static final String EGGCRACKED="EggCracked";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME,null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + ACCOUNTS + "("
                + ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + USERNAME +" TEXT, "
                + EMAIIL +" TEXT, "
                + PASSWORD +" TEXT, "
                + EGGCRACKED +" BOOLEAN )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ ACCOUNTS);
        onCreate(db);
    }

    public boolean insertData(String username, String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(EMAIIL, email);
        contentValues.put(PASSWORD, password);
        long result = db.insert(ACCOUNTS, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public long updateEgg(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EGGCRACKED, "Yes");
        return db.update(ACCOUNTS, contentValues, "Username=?",new String[] {username});
    }
    

    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM " + ACCOUNTS, null);
        return results;
    }

    public Cursor findAllData(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor results = db.rawQuery("SELECT * FROM " + ACCOUNTS + " WHERE " + USERNAME + "= '" + username + "'", null);
        return results;
    }

    public void deleteAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ACCOUNTS,null,null);
    }

}
