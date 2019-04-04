package com.iteso.sesion9.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import static com.iteso.sesion9.tools.Constant.*;

import com.iteso.sesion9.beans.Category;

import java.util.ArrayList;

public class CategoryControl {
    public ArrayList<Category> getCategories(DataBaseHandler dh){

        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Category> cat= new ArrayList<>();
        String select = "SELECT " + DataBaseHandler.KEY_CATEGORY_ID  + ","
                + DataBaseHandler.KEY_CATEGORY_NAME
                + " FROM " + DataBaseHandler.TABLE_CATEGORY;

        Cursor cursor = db.rawQuery(select, null);
        while(cursor.moveToNext()){
            Category category = new Category();
            category.setId(cursor.getInt(0));
            category.setName(cursor.getString(1));
            cat.add(category);
        }

        try{
            cursor.close();
            db.close();
        }catch (Exception e){

        }
        return cat;
    }

}
