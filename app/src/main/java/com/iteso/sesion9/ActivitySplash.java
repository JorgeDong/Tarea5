package com.iteso.sesion9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.iteso.sesion9.beans.City;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.beans.User;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.ItemProductControl;
import com.iteso.sesion9.database.StoreControl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ActivitySplash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        StoreControl storeControl = new StoreControl();
        ItemProductControl itemProductControl = new ItemProductControl();
        DataBaseHandler dh = DataBaseHandler.getInstance(ActivitySplash.this);
        ArrayList<Store> stores = storeControl.getStores(dh);

        if(stores.size() == 0){
            Store store1 = new Store(1,"BestBuy","123456789",1,551.2315,4151512.2, new City(1, "Zapopan"));
            Store store2 = new Store(2,"HomeDepot","123456789", 2,195.28442,-484.0739, new City(2, "Tonala"));
            Store store3 = new Store(3,"Liverpool","123456789", 3,652.6755,-584.440, new City(3, "Guadalajara"));
            storeControl.addStore(store1, dh);
            storeControl.addStore(store2, dh);
            storeControl.addStore(store3, dh);
        }
        Intent intent = new Intent(ActivitySplash.this,MainActivity.class);
        startActivity(intent);
        finish();

    }

    public User loadUser(){
        SharedPreferences sharedPreferences =
                getSharedPreferences("com.iteso.USER_PREFERENCES",
                        MODE_PRIVATE);
        User user = new User();
        user.setName(sharedPreferences.getString("NAME", null));
        user.setPassword(sharedPreferences.getString("PWD", null));
        user.setLogged(sharedPreferences.getBoolean("LOGGED", false));
        sharedPreferences = null;
        return user;
    }
}
