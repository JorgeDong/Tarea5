package com.iteso.sesion9;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.iteso.sesion9.beans.Category;
import com.iteso.sesion9.beans.ItemProduct;
import com.iteso.sesion9.beans.Store;
import com.iteso.sesion9.database.CategoryControl;
import com.iteso.sesion9.database.DataBaseHandler;
import com.iteso.sesion9.database.StoreControl;

import java.util.ArrayList;

public class ActivityItem extends AppCompatActivity {
    private Spinner image, category, store;
    private EditText title;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        DataBaseHandler dh = DataBaseHandler.getInstance(this);
       StoreControl storeControl = new StoreControl();
        CategoryControl categoryControl = new CategoryControl();

        final String[] images = getResources().getStringArray(R.array.images);
        final ArrayList<Category> categories_item = categoryControl.getCategories(dh);
        final ArrayList<Store> stores_items = storeControl.getStores(dh);

        image = findViewById(R.id.activity_item_images);
        title = findViewById(R.id.activity_item_title);
        category = findViewById(R.id.activity_item_categories);
        store = findViewById(R.id.activity_item_stores);
        save = findViewById(R.id.activity_item_save);



        final ArrayList<String> categories = new ArrayList<>(), stores = new ArrayList<>();
        for(Category category : categories_item)
            categories.add(category.getName());

        for(Store store : stores_items)
            stores.add(store.getName());

        image.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, images));

        category.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, categories.toArray()));

        store.setAdapter(new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, stores.toArray()));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemTitle = title.getText().toString();
                Category itemCategory = new Category();
                Store itemStore = new Store();
                Integer itemImage = image.getSelectedItemPosition() + 1;

                for(Store st : stores_items)
                    if(st.getName().equals(store.getSelectedItem().toString()))
                        itemStore = st;

                for(Category cat : categories_item)
                    if(cat.getName().equals(category.getSelectedItem().toString()))
                        itemCategory = cat;


                ItemProduct itemProduct = new ItemProduct(itemTitle, itemImage, itemStore, itemCategory);
                Intent intent = new Intent();
                intent.putExtra("item",itemProduct);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
