package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateFood extends AppCompatActivity {

    //declaration of all variables needed in this class
    DatabaseHelper db;
    Button searchbtn;
    Button update;
    Button delete;
    EditText search;
    EditText food;
    EditText description;
    EditText price;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_foods,searchfoods, newfood, newdescription,newprice;
    String get_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update_food );
        db = new DatabaseHelper( this );
        searchbtn = findViewById( R.id.search_food_btn );
        search = findViewById( R.id.search_d );
        food= findViewById( R.id.upfood );
        description = findViewById( R.id.upDescription );
        price = findViewById( R.id.upprice );
        update=findViewById( R.id.button2 );
        delete=findViewById( R.id.buttondelete );

        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFood();
            }
        } );
        searchbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                food();
            }
        } );

        update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        } );

    }
//this method is used to update a food item
    private void update( ) {
        databaseHelper= new DatabaseHelper( getApplicationContext() );
        sqLiteDatabase= databaseHelper.getWritableDatabase();
        String foods, desc, prices;
        foods=food.getText().toString();
        desc=description.getText().toString();
        prices=price.getText().toString();
        int count= databaseHelper.updatedFood( search_foods, foods,desc,prices,sqLiteDatabase );
        Toast.makeText( getApplicationContext(), count +" Food updated.", Toast.LENGTH_LONG ).show();
        finish();
    }
// this method selects all the food items and displays them
    public void food(){
        search_foods= description.getText().toString();
        databaseHelper= new DatabaseHelper(getApplicationContext());
        sqLiteDatabase= databaseHelper.getReadableDatabase();
        Cursor cursor= databaseHelper.review(search_foods, sqLiteDatabase);



        if (cursor.moveToFirst()) {
            //get all the food item and display to the edit textboxes food, description and price
            String FOOD = cursor.getString( 0 );
            String D = cursor.getString( 1 );
            String P=cursor.getString( 2 );
            //display the food item here
            food.setText( FOOD );
            description.setText( D );
            price.setText(P);

        } else {
            Toast.makeText( getBaseContext(), " Could not find a review, Please enter other description", Toast.LENGTH_LONG ).show();
            DatabaseUtils.dumpCursorToString( cursor );
            cursor.getPosition();
            Log.v( "Cursor object", DatabaseUtils.dumpCursorToString( cursor ) );
        }

    }
//a methdd to delete a food item
   public void deleteFood(){
       databaseHelper= new DatabaseHelper( getApplicationContext() );
       sqLiteDatabase= databaseHelper.getWritableDatabase();

       Toast.makeText( getApplicationContext(),  " Food deleted.", Toast.LENGTH_LONG ).show();

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
