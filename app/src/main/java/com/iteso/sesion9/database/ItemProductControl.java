package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.iteso.sesion9.beans.ItemProduct;
import java.util.ArrayList;

public class ItemProductControl {



    public CategoryControl categoryControl = new CategoryControl();
    public StoreControl storeControl = new StoreControl();

    public void addItemProduct(ItemProduct itemProduct, DataBaseHandler dh){
        int is=0;
        int ip=0;
        SQLiteDatabase db = dh.getWritableDatabase();
        ContentValues values = new ContentValues();

        String getCountProduct = "SELECT COUNT(" + DataBaseHandler.KEY_PRODUCT_ID
                + ") FROM " + DataBaseHandler.TABLE_PRODUCT;
        Cursor cursor_nip = db.rawQuery(getCountProduct, null);
        cursor_nip.moveToNext();
        ip = cursor_nip.getInt(0) + 1;

        itemProduct.setCode(ip);
        values.put(DataBaseHandler.KEY_PRODUCT_ID, ip);
        values.put(DataBaseHandler.KEY_PRODUCT_TITLE, itemProduct.getTitle());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        values.put(DataBaseHandler.KEY_PRODUCT_CATEGORY, itemProduct.getCategory().getId());
        values.put(DataBaseHandler.KEY_PRODUCT_IMAGE, itemProduct.getImage());
        db.insert(DataBaseHandler.TABLE_PRODUCT,null,values);
        values.clear();

        String getCountStoreProduct = "SELECT COUNT(" + DataBaseHandler.KEY_STORE_PRODUCT_ID
                + ") FROM " + DataBaseHandler.TABLE_STORE_PRODUCT;

        Cursor cursor_nisp = db.rawQuery(getCountStoreProduct, null);
        cursor_nisp.moveToNext();
        is = cursor_nisp.getInt(0) + 1;
        values.put(DataBaseHandler.KEY_STORE_PRODUCT_ID, ip);
        values.put(DataBaseHandler.KEY_STORE_PRODUCT_ID, itemProduct.getStore().getId());
        values.put(DataBaseHandler.KEY_STORE_PRODUCT_ID, itemProduct.getCode());
        db.insert(DataBaseHandler.TABLE_STORE_PRODUCT,null,values);
        try{
            db.close();
        }catch (Exception e){

        }
    }
    public ArrayList<ItemProduct> getItemProductsByCategory(int idCategory, DataBaseHandler dh){
        ArrayList<ItemProduct> itemProducts = new ArrayList<>();
        SQLiteDatabase db = dh.getReadableDatabase();
        String select = "SELECT p." + DataBaseHandler.KEY_PRODUCT_ID + ","
                + DataBaseHandler.KEY_PRODUCT_TITLE + ","
                + DataBaseHandler.KEY_PRODUCT_IMAGE + ","
                + DataBaseHandler.KEY_STORE_PRODUCT_ID_STORE + ","
                + DataBaseHandler.KEY_PRODUCT_ID
                + " FROM " + DataBaseHandler.TABLE_PRODUCT + " p JOIN "
                + DataBaseHandler.TABLE_STORE_PRODUCT + " sp ON p."
                + DataBaseHandler.KEY_PRODUCT_ID + " = sp." + DataBaseHandler.KEY_STORE_PRODUCT_ID
                + " WHERE " + DataBaseHandler.KEY_PRODUCT_ID + " = "+ idCategory;
        Cursor cursor = db.rawQuery(select, null);

        while(cursor.moveToNext()){
            ItemProduct itemProduct = new ItemProduct();
            itemProduct.setCode(cursor.getInt(0));
            itemProduct.setTitle(cursor.getString(1));
            itemProduct.setImage(cursor.getInt(2));
            itemProduct.setStore(storeControl.getStoreById(cursor.getInt(3), dh));
            itemProducts.add(itemProduct);
        }

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }

        return itemProducts;
    }

}
