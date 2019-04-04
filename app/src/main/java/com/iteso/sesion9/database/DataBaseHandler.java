package com.iteso.sesion9.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.iteso.sesion9.tools.Constant.*;


public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ItesoStore.db";
    private static final int DATABASE_VERSION = 1;

    // Tablas
    public static final String TABLE_STORE = "store";
    public static final String TABLE_PRODUCT = "product";
    public static final String TABLE_CITY = "city";
    public static final String TABLE_CATEGORY = "category";
    public static final String TABLE_STORE_PRODUCT = "store_product";


    // Tabla Ciudades
    public static final String KEY_CITY_ID = "idCity";
    public static final String KEY_CITY_NAME = "name";

    // Tabla categorias
    public static final String KEY_CATEGORY_ID = "idCategory";
    public static final String KEY_CATEGORY_NAME = "name";

    // Tabla productos
    public static final String KEY_PRODUCT_ID = "idProduct";
    public static final String KEY_PRODUCT_TITLE = "name";
    public static final String KEY_PRODUCT_IMAGE = "image";
    public static final String KEY_PRODUCT_CATEGORY = "idCategory";


    // tabla Tienda
    public static final String KEY_STORE_ID = "idStore";
    public static final String KEY_STORE_NAME = "name";
    public static final String KEY_STORE_PHONE = "phone";
    public static final String KEY_STORE_CITY = "idCity";
    public static final String KEY_STORE_THUMBNAIL = "thumbnail";
    public static final String KEY_STORE_LAT = "latitude";
    public static final String KEY_STORE_LNG = "longitude";

    // Tabla store Product
    public static final String KEY_STORE_PRODUCT_ID = "idStoreProduct";
    public static final String KEY_STORE_PRODUCT_ID_PRODUCT = "idProduct";
    public static final String KEY_STORE_PRODUCT_ID_STORE = "idStore";

    private static DataBaseHandler dataBaseHandler;

    private DataBaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHandler getInstance(Context context){
        if(dataBaseHandler == null){
            //Initialize
            dataBaseHandler = new DataBaseHandler(context);
        }
        return dataBaseHandler;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Crear tabla Ciudades
        String CREATE_CITY_TABLE = "CREATE TABLE " + TABLE_CITY+ "("
                + KEY_CITY_ID + " INTEGER PRIMARY KEY,"
                + KEY_CITY_NAME + " TEXT)";
        db.execSQL(CREATE_CITY_TABLE);

        // Crear tabla Categorias
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY+ "("
                + KEY_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CATEGORY_NAME + " TEXT)";
        db.execSQL(CREATE_CATEGORY_TABLE);

        // crear Tabla tiendas
        String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_STORE+ "("
                + KEY_STORE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STORE_NAME + " TEXT,"
                + KEY_STORE_PHONE + " TEXT,"
                + KEY_STORE_CITY + " INTEGER,"
                + KEY_STORE_THUMBNAIL + " INTEGER,"
                + KEY_STORE_LAT + " DOUBLE,"
                + KEY_STORE_LNG + " DOUBLE)";
        db.execSQL(CREATE_STORE_TABLE);

        //crear tabla productos
        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT+ "("
                + KEY_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_PRODUCT_TITLE + " TEXT,"
                + KEY_PRODUCT_IMAGE + " INTEGER,"
                + KEY_PRODUCT_CATEGORY + " INTEGER)";
        db.execSQL(CREATE_PRODUCT_TABLE);

        // Crear Tablas store Product

        String CREATE_STORE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_STORE_PRODUCT+ "("
                + KEY_STORE_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_STORE_PRODUCT_ID_PRODUCT + " INTEGER,"
                + KEY_STORE_PRODUCT_ID_STORE + " INTEGER)";
        db.execSQL(CREATE_STORE_PRODUCT_TABLE);


        //Insertar Ciudades Por default
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (1, 'El Salto')")
        ; db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (2, 'Guadalajara')");
        db.execSQL("INSERT INTO " + TABLE_CITY+ " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (3, 'Ixtlahuacán de los Membrillos')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (4, 'Juanacatlán')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (5, 'San Pedro Tlaquepaque')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (6, 'Tlajomulco')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (7, 'Tonalá')");
        db.execSQL("INSERT INTO " + TABLE_CITY + " (" + KEY_CITY_ID + "," + KEY_CITY_NAME + ") VALUES (8, 'Zapopan')");

// Insertar categorias
        db.execSQL("INSERT INTO " + TABLE_CATEGORY+ " (" + KEY_CATEGORY_NAME + "," + KEY_CATEGORY_ID + ") VALUES ('TECHNOLOGY',0)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY + " (" + KEY_CATEGORY_NAME + "," + KEY_CATEGORY_ID + ") VALUES ('HOME',1)");
        db.execSQL("INSERT INTO " + TABLE_CATEGORY+ " (" + KEY_CATEGORY_NAME + "," + KEY_CATEGORY_ID + ") VALUES ('ELECTRONICS',2)");


        db.execSQL("INSERT INTO " + TABLE_STORE + " (" + KEY_STORE_NAME + "," + KEY_STORE_PHONE + ","
                + KEY_STORE_CITY + "," + KEY_STORE_THUMBNAIL + ","
                + KEY_STORE_LAT + "," + KEY_STORE_LNG + ") VALUES ('BESTBUY', '01 800 237 8289', 2, 0, 20.6489713, -103.4207757)");


        onUpgrade(db, 1, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                upgradeV2(db);
            case 2:
                String alterAddress = "ALTER Table ItesoStore ADD address TEXT";
                db.execSQL(alterAddress);
                break;
        }
    }

    private void upgradeV2(SQLiteDatabase db){
        String alterTable = "ALTER Table Student ADD phone TEXT";
        db.execSQL(alterTable);
    }
}










