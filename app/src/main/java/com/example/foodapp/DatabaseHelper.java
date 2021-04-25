package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.foodapp.foodContract.newFood;
//the class is used to create the database and perform CRUD functions
public class DatabaseHelper extends SQLiteOpenHelper {
// create the database


    private static  final  String FOODDB= "FOODDB.DB";
    private  static  final int DATABASEVERSIONS= 1;
    private static  final  String CREATE_QUERY=
            " CREATE TABLE " + newFood.TABLE_NAME +"("+ newFood.FOOD+ " TEXT, " + newFood.DESCRIPTION + " TEXT,"+ newFood.PRICE + " TEXT,"+ newFood.RATING + " FLOAT);";
    public DatabaseHelper(Context context) {
        super(context, FOODDB, null, 1);
        Log.e("database operations", "database created/opened...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( CREATE_QUERY );
        Log.e("database operations", "Table created...");

    }
//Inserts  data into the database
    public  void insertD( String FOOD, String DESCRIPTION, String PRICE,Float RATING,SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues= new ContentValues(  );
        contentValues.put( newFood.FOOD,FOOD );
        contentValues.put( newFood.DESCRIPTION,DESCRIPTION );
        contentValues.put( newFood.PRICE,PRICE );
        contentValues.put( newFood.RATING,RATING );

        sqLiteDatabase.insert( newFood.TABLE_NAME, null, contentValues );
        Log.e("database operations", "One row is inserted..");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void deletFood(){
        ContentValues contentValues= new ContentValues(  );
        contentValues.clear();

    }


    public Cursor review(String Food, SQLiteDatabase sqLiteDatabase){
        String[] projections = {newFood.FOOD, newFood.DESCRIPTION, newFood.PRICE};
        String selection= newFood.DESCRIPTION + " LIKE '%'||?||'%'";
        String[] selection_args={Food};
        Cursor cursor= sqLiteDatabase.query( newFood.TABLE_NAME,projections, selection,selection_args,null, null, null);
        if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
            Log.e("database operations", "NO NO data selected..");
        }
        return cursor;

    }
    //gets all food items from the database
    public  Cursor getall(SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String[] projections= {newFood.FOOD, newFood.DESCRIPTION, newFood.PRICE,newFood.RATING};
        cursor=sqLiteDatabase.query( newFood.TABLE_NAME, projections, null, null, null, null, null );
        return cursor;

    }
    //updates a food item
    public int updatedFood(String oldFood, String newFood, String newDesc, String newPrice, SQLiteDatabase sqLiteDatabase){
        ContentValues contentValues= new ContentValues(  );
        contentValues.put( foodContract.newFood.FOOD,newFood );
        contentValues.put( foodContract.newFood.DESCRIPTION,newDesc);
        contentValues.put( foodContract.newFood.PRICE,newPrice );

        String selection= foodContract.newFood.DESCRIPTION+ " LIKE? ";
        String[] selection_args={oldFood};

        int count= sqLiteDatabase.update( foodContract.newFood.TABLE_NAME,contentValues, selection,selection_args );

        return count;


    }



}
