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
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //declaraton of variables
    DatabaseHelper db;
    Button button;
    Button view_food;
    Button delete;
    EditText description;
    Button button2;
    TextView viewFood;
    TextView viewDe;
    TextView view2;
    TextView viewPrices;
    Button viewall;


    //SEditText viewDisease;



    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_food;
    String get_all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        //assigning the variables their corresponding activity items IDs
        db = new DatabaseHelper( this );
        button = findViewById( R.id.enter );
        view_food = findViewById( R.id.view_food );
        viewPrices= findViewById( R.id.viewPrice );
        description = findViewById( R.id.search_d );
        button2 = findViewById( R.id.button2 );
        viewFood= findViewById( R.id.viewFood );
        viewDe= findViewById( R.id.viewDesc );
        viewall= findViewById( R.id.viewall );

        delete=findViewById( R.id.buttondelete );

        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFood();
            }
        } );

        //adds a click listenter to the
        view_food.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              food();
            }
        } );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedFood();
            }
        } );



        //open map click action
        button.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){

                openMap();

            }
        });


    }

       // Open map method
        public void openMap(){
            Intent intent=new Intent(this, maps.class);
            startActivity(intent);
        }
        public void food(){
            search_food= description.getText().toString();
            databaseHelper= new DatabaseHelper(getApplicationContext());
            sqLiteDatabase= databaseHelper.getReadableDatabase();
            Cursor cursor= databaseHelper.review(search_food, sqLiteDatabase);



                if (cursor.moveToFirst()) {
                    String FOOD = cursor.getString( 0 );
                    String D = cursor.getString( 1 );
                    String P=cursor.getString( 2 );
                    viewFood.setText( FOOD );
                    viewDe.setText( D );
                    viewPrices.setText(P);

                } else {
                    Toast.makeText( getBaseContext(), " Could not find a review, Please enter other description", Toast.LENGTH_LONG ).show();
                    DatabaseUtils.dumpCursorToString( cursor );
                    cursor.getPosition();
                    Log.v( "Cursor object", DatabaseUtils.dumpCursorToString( cursor ) );
                }

        }
    //a method to delete a food item
    public void deleteFood(){
        databaseHelper= new DatabaseHelper( getApplicationContext() );
        sqLiteDatabase= databaseHelper.getWritableDatabase();

        Toast.makeText( getApplicationContext(),  " Food deleted.", Toast.LENGTH_LONG ).show();

    }

        //save
    public  void savedFood(){
        Intent intent= new Intent(this, insert.class);
        startActivity(intent);
    }

    public  void viewAll( View view){
        Intent intent= new Intent(this, foods.class);
        startActivity(intent);
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
//used to open the update page
    private void upDate() {
        Intent intent= new Intent(this, UpdateFood.class);
        startActivity(intent);
    }
//opens view all page
    private void viewAll() {
        Intent intent= new Intent(this, foods.class);
        startActivity(intent);
    }
//closes the application
    public void exit(){
        System.exit( 0 );
    }

}
