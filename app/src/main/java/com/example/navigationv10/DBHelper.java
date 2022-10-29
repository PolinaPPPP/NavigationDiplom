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
        DB.execSQL("create Table Housedetails(house_name TEXT primary key, house_gk TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists GKdetails");
        DB.execSQL("drop Table if exists Housedetails");

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
    public Boolean inserthousedata(String house_name, String house_gk)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("house_name", house_name);
        contentValues.put("house_gk", house_gk);



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

    //Передаём название ЖК в форму для Домов//

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

}
