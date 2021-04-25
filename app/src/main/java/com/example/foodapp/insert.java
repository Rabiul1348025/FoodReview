package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class insert extends AppCompatActivity {
    EditText foo, description, price;
    Context context= this;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button update, button;
    RatingBar ratingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert );
        foo=findViewById( R.id.foods );
        description=(EditText)findViewById( R.id.descriptionD );
        price=(EditText) findViewById( R.id.prices );
        update=findViewById( R.id.button2 );
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        button=(Button)findViewById(R.id.button);



    }

    public  void  addFood( View view){

        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                //Getting the rating and displaying it on the toast
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
            }

        });

        String food=  foo.getText().toString();
        String desc= description.getText().toString();
        String pric= price.getText().toString();
        Float rating=ratingbar.getRating();
        databaseHelper = new DatabaseHelper( context );
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        databaseHelper.insertD( food, desc,pric,rating,sqLiteDatabase );

        Toast.makeText( getBaseContext(), " Data Saved", Toast.LENGTH_LONG ).show();
        databaseHelper.close();

        //Performing action on Button Click

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
