package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {



    private static  final  String FOODDB= "FOODDB.DB";
    private  static  final int DATABASEVERSIONS= 1;
    private static  final  String CREATE_QUERY=
            " CREATE TABLE " + foodContract.newFood.TABLE_NAME +"("+ foodContract.newFood.FOOD+ " TEXT, " + foodContract.newFood.DESCRIPTION + " TEXT,"+ foodContract.newFood.PRICE + " TEXT);";
    public DatabaseHelper(Context context) {
        super(context, FOODDB, null, 1);
        Log.e("database operations", "database created/opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( CREATE_QUERY );
        Log.e("database operations", "Table created...");

    }
//IF ERROR CHECK variable NAME CASE
    public  void insertD( String FOOD, String DESCRIPTION,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues= new ContentValues(  );
        contentValues.put( foodContract.newFood.FOOD,FOOD );
        contentValues.put( foodContract.newFood.DESCRIPTION,DESCRIPTION );

        sqLiteDatabase.insert( foodContract.newFood.TABLE_NAME, null, contentValues );
        Log.e("database operations", "One row is inserted..");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public Cursor review(String Food, SQLiteDatabase sqLiteDatabase){
        String[] projections = {foodContract.newFood.FOOD, foodContract.newFood.DESCRIPTION,foodContract.newFood.PRICE};
        String selection= foodContract.newFood.DESCRIPTION + " LIKE '%'||?||'%'";
        String[] selection_args={Food};
        Cursor cursor= sqLiteDatabase.query(foodContract.newFood.TABLE_NAME,projections, selection,selection_args,null, null, null);
        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            Log.e("database operations", "NO NO data selected..");
        }


        return cursor;

    }
    public  Cursor getall(SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String[] projections= {foodContract.newFood.FOOD, foodContract.newFood.DESCRIPTION, foodContract.newFood.PRICE};
        cursor=sqLiteDatabase.query( foodContract.newFood.TABLE_NAME, projections, null, null, null, null, null );
        return cursor;

    }

}
