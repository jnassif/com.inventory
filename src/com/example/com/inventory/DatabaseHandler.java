package com.example.com.inventory;


import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "inventory";

    // Contacts table name
    private static final String TABLE_WAREHOUSE = "warehouse";
    
    // Contacts Table Columns names
    private static final String KEY_ID = "warehouse";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";
    private final ArrayList<Warehouse> warehouse_list = new ArrayList<Warehouse>();
    
    
    public DatabaseHandler(Context context) {
    	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
		String CREATE_WAREHOUSE_TABLE = "CREATE TABLE " + TABLE_WAREHOUSE + "("
			+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
			+ KEY_ADDRESS + " TEXT" + ")";
		db.execSQL(CREATE_WAREHOUSE_TABLE);
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAREHOUSE);
		// Create tables again
		onCreate(db);
	}
	
	// Adding new warehouse
	public void Add_warehouse(Warehouse warehouse) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, warehouse.get_name()); // warehouse Name
		values.put(KEY_ADDRESS, warehouse.get_address()); // warehouse address Phone
		
		// Inserting Row
		db.insert(TABLE_WAREHOUSE, null, values);
		db.close(); // Closing database connection
	}
	
    // Getting single warehouse
    Warehouse Get_warehouse(int id) {
	SQLiteDatabase db = this.getReadableDatabase();

	Cursor cursor = db.query(TABLE_WAREHOUSE, new String[] { KEY_ID,
		KEY_NAME, KEY_ADDRESS}, KEY_ID + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
	if (cursor != null)
	    cursor.moveToFirst();

	Warehouse warehouse = new Warehouse(Integer.parseInt(cursor.getString(0)),
		cursor.getString(1), cursor.getString(2));
	// return contact
	cursor.close();
	db.close();

	return warehouse;
    }
   
    // Getting All warehouses
    public ArrayList<Warehouse> Get_Warehouses() {
		try {
		    warehouse_list.clear();
	
		    // Select All Query
		    String selectQuery = "SELECT  * FROM " + TABLE_WAREHOUSE;
	
		    SQLiteDatabase db = this.getWritableDatabase();
		    Cursor cursor = db.rawQuery(selectQuery, null);
	
		    // looping through all rows and adding to list
		    if (cursor.moveToFirst()) {
			do {
				Warehouse warehouse = new Warehouse();
				warehouse.set_warehouse(Integer.parseInt(cursor.getString(0)));
				warehouse.set_name(cursor.getString(1));
				warehouse.set_address(cursor.getString(2));
			   
			    // Adding warehouse to list
				warehouse_list.add(warehouse);
			} while (cursor.moveToNext());
		   }
	
		    // return warehouse list
		    cursor.close();
		    db.close();
		    return warehouse_list;
		} catch (Exception e) {
		    // TODO: handle exception
		    Log.e("all_contact", "" + e);
		}

	return warehouse_list;
    }
    
    // Updating single wwarehouse
    public int Update_warehouse(Warehouse warehouse) {
	SQLiteDatabase db = this.getWritableDatabase();

	ContentValues values = new ContentValues();
	values.put(KEY_NAME, warehouse.get_name());
	values.put(KEY_ADDRESS, warehouse.get_address());
	
	// updating row
	return db.update(TABLE_WAREHOUSE, values, KEY_ID + " = ?",
		new String[] { String.valueOf(warehouse.get_warehouse()) });
    }
    
    // Deleting single warehouse
    public void Delete_warehouse(int id) {
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_WAREHOUSE, KEY_ID + " = ?",
		new String[] { String.valueOf(id) });
	db.close();
    }
    
    // Getting warehouse Count
    public int Get_Total_Contacts() {
		String countQuery = "SELECT  * FROM " + TABLE_WAREHOUSE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();
	
		// return count
		return cursor.getCount();
    }
    
}
