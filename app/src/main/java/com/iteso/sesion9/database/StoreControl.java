package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iteso.sesion9.beans.Store;
import java.util.ArrayList;

public class StoreControl {
    public CityControl cityControl = new CityControl();

    public void addStore(Store store, DataBaseHandler dh){
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DataBaseHandler.KEY_STORE_ID, store.getId());
        values.put(DataBaseHandler.KEY_STORE_NAME, store.getName());
        values.put(DataBaseHandler.KEY_STORE_PHONE, store.getPhone());
        values.put(DataBaseHandler.KEY_STORE_CITY , store.getCity().getId());
        values.put(DataBaseHandler.KEY_STORE_THUMBNAIL, store.getThumbnail());
        values.put(DataBaseHandler.KEY_STORE_LAT, store.getLatitude());
        values.put(DataBaseHandler.KEY_STORE_LNG, store.getLongitude());

        db.insert(DataBaseHandler.TABLE_STORE,null,values);

        try{
            db.close();
        }catch (Exception e){

        }
    }

    public ArrayList<Store> getStores(DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Store> stores = new ArrayList<>();
        String select = "SELECT " + DataBaseHandler.KEY_STORE_ID + ","
                + DataBaseHandler.KEY_STORE_NAME + ","
                + DataBaseHandler.KEY_STORE_PHONE + ","
                + DataBaseHandler.KEY_STORE_CITY  + ","
                + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + DataBaseHandler.KEY_STORE_LAT + ","
                + DataBaseHandler.KEY_STORE_LNG
                + " FROM " + DataBaseHandler.TABLE_STORE;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Store store = new Store();
            store.setId(cursor.getInt(0));
            store.setName(cursor.getString(1));
            store.setPhone(cursor.getString(2));
            store.setCity(cityControl.getCityById(cursor.getInt(3), dh));
            store.setThumbnail(cursor.getInt(4));
            store.setLatitude(cursor.getDouble(5));
            store.setLongitude(cursor.getDouble(6));
            stores.add(store);
        }

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return stores;
    }

    public Store getStoreById(int id, DataBaseHandler dh){
        SQLiteDatabase db = dh.getReadableDatabase();
        Store store = new Store();
        String select = "SELECT " + DataBaseHandler.KEY_STORE_ID + ","
                + DataBaseHandler.KEY_STORE_NAME + ","
                + DataBaseHandler.KEY_STORE_PHONE + ","
                + DataBaseHandler.KEY_STORE_CITY + ","
                + DataBaseHandler.KEY_STORE_THUMBNAIL + ","
                + DataBaseHandler.KEY_STORE_LAT + ","
                + DataBaseHandler.KEY_STORE_LNG
                + " FROM " + DataBaseHandler.TABLE_STORE
                + " WHERE " + DataBaseHandler.KEY_STORE_ID + " = "+ id;

        Cursor cursor = db.rawQuery(select, null);

        cursor.moveToNext();

        store.setId(cursor.getInt(0));
        store.setName(cursor.getString(1));
        store.setPhone(cursor.getString(2));
        store.setThumbnail(cursor.getInt(4));
        store.setLatitude(cursor.getDouble(5));
        store.setLongitude(cursor.getDouble(6));

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }
        return store;
    }

}
