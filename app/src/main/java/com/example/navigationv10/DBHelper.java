package com.example.navigationv10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, email TEXT, phone TEXT)");
        DB.execSQL("create Table GKdetails(gk_name TEXT primary key, gk_adress TEXT, name_zas TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists GKdetails");


    }

    public Boolean insertuserdata(String name, String email, String phone )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        long result = DB.insert("Userdetails", null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    public Boolean insertgkdata(String gk_name, String gk_adress, String name_zas )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gk_name", gk_name);
        contentValues.put("gk_adress", gk_adress);
        contentValues.put("name_zas", name_zas);


        long result = DB.insert("GKdetails", null, contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }




    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select * from Userdetails", null);

        return cursor;
    }

    public Cursor getdatagk(String name_zastroishick){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from GKdetails WHERE name_zas=\'" + name_zastroishick + "\'", null);
        return cursor;
    }







}
