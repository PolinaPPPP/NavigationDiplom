package com.example.navigationv10;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdetails(name TEXT primary key, email TEXT, phone TEXT)");
        DB.execSQL("create Table GKdetails(gk_name TEXT primary key, gk_adress TEXT, name_zas TEXT, gk_website TEXT)");
        DB.execSQL("create Table Housedetails(house_name TEXT primary key, house_gk TEXT, data TEXT , otdelka TEXT)");
        DB.execSQL("create Table Sectiondetails(section_namber TEXT primary key, section_house TEXT, section_floor,section_sum )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists GKdetails");
        DB.execSQL("drop Table if exists Housedetails");
        DB.execSQL("drop Table if exists Sectiondetails");
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
    public Boolean insertgkdata(String gk_name, String gk_adress, String name_zas, String gk_website)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gk_name", gk_name);
        contentValues.put("gk_adress", gk_adress);
        contentValues.put("name_zas", name_zas);
        contentValues.put("gk_website", gk_website);


        long result = DB.insert("GKdetails", null, contentValues);
        if(result==-1)
        {
            Log.d("insertgkdata", "result false "+result);
            return  false;
        }
        else
        {
            Log.d("insertgkdata", "result true "+result);
            return true;
        }
    }

    public Boolean inserthousedata(String house_name, String house_gk , String data, String otdelka )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("house_name", house_name);
        contentValues.put("house_gk", house_gk);
        contentValues.put("data", data);
        contentValues.put("otdelka", otdelka);


        long result = DB.insert("Housedetails", null, contentValues);
        if(result==-1)
        {
            Log.d("inserthousedata", "result false "+result);
            return  false;
        }
        else
        {
            Log.d("inserthousedata", "result true "+result);
            return true;
        }
    }

    public Boolean insertsectiondata(String section_namber, String section_house , String section_floor, String section_sum )
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("section_namber", section_namber);
        contentValues.put("section_house", section_house);
        contentValues.put("section_floor", section_floor);
        contentValues.put("section_sum", section_sum);


        long result = DB.insert("Sectiondetails", null, contentValues);
        if(result==-1)
        {
            Log.d("insertsectiondata", "result false "+result);
            return  false;
        }
        else
        {
            Log.d("insertsectiondata", "result true "+result);
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
        Cursor cursor = DB.rawQuery("Select gk_name,name_zas from GKdetails WHERE name_zas=\'" + name_zastroishick + "\'", null);
        Log.d("getdatagk", "val cursor.getCount()= "+cursor.getCount());
        return cursor;
    }

    public Cursor getdatagkinfo(String name_complexk)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        String exec = "SELECT gk_name,gk_adress,gk_website FROM GKdetails WRERE gk_name=\'" +  name_complexk + "\'";
        Log.d("exec",exec);
        Cursor cursor  = DB.rawQuery("Select gk_name,gk_adress,gk_website from GKdetails WHERE gk_name=\'" + name_complexk + "\'", null);

        return cursor;
    }



    public Cursor getdatagkname()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select gk_name from GKdetails", null);

        return cursor;
    }


    public Cursor getdatahouse(String gk_complex)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select house_name from Housedetails WHERE house_gk=\'" + gk_complex + "\'", null);

        return cursor;
    }


    public Cursor getdatahouseinfo(String housename)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select house_name,house_gk,data,otdelka from Housedetails WHERE house_name=\'" + housename + "\'", null);

        return cursor;
    }

    public Cursor getdatahousename()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select house_name from Housedetails", null);

        return cursor;
    }

    public Cursor getdatasection(String house_complex)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor  = DB.rawQuery("Select section_namber from Sectiondetails WHERE section_house=\'" + house_complex + "\'", null);

        return cursor;
    }

}
