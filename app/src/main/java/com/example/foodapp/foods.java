package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class foods extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper databaseHelper;
    Cursor cursor;
    ListDataAdapter listDataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_foods );

        listView= (ListView) findViewById( R.id.list_view );
        listDataAdapter= new ListDataAdapter( getApplicationContext(), R.layout.row_layout );
        listView.setAdapter( listDataAdapter );
        databaseHelper= new DatabaseHelper(getApplicationContext()  );
        sqLiteDatabase= databaseHelper.getReadableDatabase();
        cursor= databaseHelper.getall( sqLiteDatabase );


        if (cursor.moveToFirst()){

            do {
                String food, desc, pric;
                float rates;
                food=cursor.getString( 0 );
                desc= cursor.getString( 1 );
                pric= cursor.getString( 2 );
                rates=cursor.getFloat( 3 );
                DataProvider dataProvider= new DataProvider( food,desc,pric,rates );
                listDataAdapter.add( dataProvider );
            }while (cursor.moveToNext());
        }

    }
    // this method creates a menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    //this method helps to select the menu options, it selects the method the menu is intended for
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                exit();

                return true;
            case R.id.item2:
                upDate();
                return true;
            case R.id.item3:
                viewAll();

                return true;
            case R.id.item4:
                viewMap();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //this methods are used to navigate the menu options
    private void viewMap() {
        Intent intent= new Intent(this, maps.class);
        startActivity(intent);
    }

    private void upDate() {
        Intent intent= new Intent(this, UpdateFood.class);
        startActivity(intent);
    }

    private void viewAll() {
        Intent intent= new Intent(this, foods.class);
        startActivity(intent);
    }

    public void exit(){
        System.exit( 0 );
    }
}
