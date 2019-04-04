package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.City;
import java.util.ArrayList;

public class CityControl {

    public void addCity(City city, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_CITY_ID, city.getId());
        values.put(DataBaseHandler.KEY_CITY_NAME, city.getName());
        db.insert(DataBaseHandler.TABLE_CITY,null,values);

        try{
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<City> getCities(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<City> cities = new ArrayList<>();
        String select = "SELECT " + DataBaseHandler.KEY_CITY_ID + ","
                + DataBaseHandler.KEY_CITY_NAME
                + " FROM " + DataBaseHandler.TABLE_CITY;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            City city = new City();
            city.setId(cursor.getInt(0));
            city.setName(cursor.getString(1));
            cities.add(city);
        }

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }
        return cities;
    }

    public City getCityById(int id, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        City city = new City();
        String select = "SELECT " + DataBaseHandler.KEY_CITY_ID + ","
                + DataBaseHandler.KEY_CITY_NAME
                + " FROM " + DataBaseHandler.TABLE_CITY
                + " WHERE " + DataBaseHandler.KEY_CITY_ID + " = "+ id;

        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToNext();
        city.setId(cursor.getInt(0));
        city.setName(cursor.getString(1));

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return city;
    }
}
