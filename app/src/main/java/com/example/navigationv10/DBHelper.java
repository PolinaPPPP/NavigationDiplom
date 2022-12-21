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
        DB.execSQL("create Table GKdetails(gk_name TEXT primary key, gk_adress TEXT, name_zas TEXT, gk_website TEXT, gk_bild TEXT, gk_territory TEXT, gk_parking TEXT)");
        DB.execSQL("create Table Housedetails(house_name TEXT primary key, house_gk TEXT, data TEXT , otdelka TEXT, material TEXT, otopleniye TEXT, floor TEXT, potolok TEXT, elevator TEXT, section TEXT, concierge TEXT, glass TEXT, property_class TEXT )");
        DB.execSQL("create Table Sectiondetails(section_namber TEXT primary key, section_house TEXT, section_floor TEXT,section_sum TEXT,number_kv TEXT)");
        DB.execSQL("create Table Roomdetails(room_name TEXT primary key, section TEXT , room_number TEXT, room_price TEXT, room_square TEXT, room_directions TEXT, room_otdelka TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdetails");
        DB.execSQL("drop Table if exists GKdetails");
        DB.execSQL("drop Table if exists Housedetails");
        DB.execSQL("drop Table if exists Sectiondetails");
        DB.execSQL("drop Table if exists Roomdetails");
    }

    public Boolean insertuserdata(String name, String email, String phone) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        long result = DB.insert("Userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean insertgkdata(String gk_name, String gk_adress, String name_zas, String gk_website, String gk_bild, String gk_territory, String gk_parking) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gk_name", gk_name);
        contentValues.put("gk_adress", gk_adress);
        contentValues.put("name_zas", name_zas);
        contentValues.put("gk_website", gk_website);
        contentValues.put("gk_bild", gk_bild);
        contentValues.put("gk_territory", gk_territory);
        contentValues.put("gk_parking", gk_parking);


        long result = DB.insert("GKdetails", null, contentValues);
        if (result == -1) {
            Log.d("insertgkdata", "result false " + result);
            return false;
        } else {
            Log.d("insertgkdata", "result true " + result);
            return true;
        }
    }

    public Boolean inserthousedata(String house_name, String house_gk, String data, String otdelka , String material , String otopleniye , String floor, String potolok , String elevator, String section, String concierge, String glass, String property_class) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("house_name", house_name);
        contentValues.put("house_gk", house_gk);
        contentValues.put("data", data);
        contentValues.put("otdelka", otdelka);
        contentValues.put("material", material);
        contentValues.put("otopleniye", otopleniye);
        contentValues.put("floor", floor);
        contentValues.put("potolok", potolok);
        contentValues.put("elevator", elevator);
        contentValues.put("section", section);
        contentValues.put("concierge", concierge);
        contentValues.put("glass", glass);
        contentValues.put("property_class", property_class);


        long result = DB.insert("Housedetails", null, contentValues);
        if (result == -1) {
            Log.d("inserthousedata", "result false " + result);
            return false;
        } else {
            Log.d("inserthousedata", "result true " + result);
            return true;
        }
    }

    public Boolean insertsectiondata(String section_namber, String section_house, String section_floor, String section_sum, String number_kv) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("section_namber", section_namber);
        contentValues.put("section_house", section_house);
        contentValues.put("section_floor", section_floor);
        contentValues.put("section_sum", section_sum);
        contentValues.put("number_kv", number_kv);

        long result = DB.insert("Sectiondetails", null, contentValues);
        if (result == -1) {
            Log.d("insertsectiondata", "result false " + result);
            return false;
        } else {
            Log.d("insertsectiondata", "result true " + result);
            return true;
        }
    }

    public Boolean insertroomdata(String room_name, String section, String room_number, String room_price, String room_square, String room_directions, String room_otdelka ) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("room_name", room_name);
        contentValues.put("section", section);
        contentValues.put("room_number", room_number);
        contentValues.put("room_price", room_price);
        contentValues.put("room_square", room_square);
        contentValues.put("room_directions", room_directions);
        contentValues.put("room_otdelka", room_otdelka);


        long result = DB.insert("Roomdetails", null, contentValues);
        if (result == -1) {
            Log.d("insertroomdata", "result false " + result);
            return false;
        } else {
            Log.d("insertroomdata", "result true " + result);
            return true;
        }
    }


    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);

        return cursor;
    }
    public Cursor getdatazas() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select name from Userdetails", null);

        return cursor;
    }

    public Cursor getdatagk(String name_zastroishick) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select gk_name,name_zas from GKdetails WHERE name_zas=\'" + name_zastroishick + "\'", null);
        Log.d("getdatagk", "val cursor.getCount()= " + cursor.getCount());
        return cursor;
    }

    public Cursor getdatagkinfo(String name_complexk) {
        SQLiteDatabase DB = this.getWritableDatabase();
        String exec = "SELECT gk_name,gk_adress,gk_website FROM GKdetails WRERE gk_name=\'" + name_complexk + "\'";
        Log.d("exec", exec);
        Cursor cursor = DB.rawQuery("Select gk_name,gk_adress,gk_website,gk_bild,gk_territory,gk_parking from GKdetails WHERE gk_name=\'" + name_complexk + "\'", null);

        return cursor;
    }


    public Cursor getdatagkname() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select gk_name from GKdetails", null);

        return cursor;
    }


    public Cursor getdatahouse(String gk_complex) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select house_name,house_gk from Housedetails WHERE house_gk=\'" + gk_complex + "\'", null);

        return cursor;
    }


    public Cursor getdatahouseinfo(String housename) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select house_name,house_gk,data,otdelka,material,otopleniye,floor,potolok,elevator,section,concierge,glass,property_class from Housedetails WHERE house_name=\'" + housename + "\'", null);

        return cursor;
    }

    public Cursor getdatahousename() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select house_name from Housedetails", null);

        return cursor;
    }

    public Cursor getdatasection(String house_complex) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select section_namber,section_house from Sectiondetails WHERE section_house=\'" + house_complex + "\'", null);

        return cursor;
    }

    public Cursor getdatasectioninfo(String housename)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        String test = "SELECT section_namber,section_house,section_floor,section_sum FROM Sectiondetails WRERE section_house=\'" + housename + "\'";
        Log.d("test", test);
        Cursor cursor = DB.rawQuery("Select section_namber,section_house,section_floor,section_sum,number_kv from Sectiondetails WHERE section_namber=\'" + housename + "\'", null);

        return cursor;
    }

    public Cursor getdatasectionnumber() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select section_namber from Sectiondetails", null);

        return cursor;
    }

    public Cursor getdataroom(String section_complex) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select room_name from Roomdetails WHERE section=\'" + section_complex + "\'", null);

        return cursor;
    }

    public Cursor getdataroominfo(String sectionnamber) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select room_name,section,room_number,room_price,room_square,room_directions,room_otdelka from Roomdetails WHERE room_name=\'" + sectionnamber + "\'", null);

        return cursor;
    }

}
