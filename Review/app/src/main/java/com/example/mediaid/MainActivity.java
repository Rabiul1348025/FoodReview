package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button button;
    Button view_food;
    Button call;
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

        db = new DatabaseHelper( this );

        button = findViewById( R.id.enter );
        view_food = findViewById( R.id.view_food );
        viewPrices= findViewById( R.id.viewPrice );
        description = findViewById( R.id.search_d );
        button2 = findViewById( R.id.button2 );
        viewFood= findViewById( R.id.viewFood );
        viewDe= findViewById( R.id.viewDex );
        view2= findViewById( R.id.textView2 );
        view2.setTextColor( Color.RED );
        viewall= findViewById( R.id.viewall );
        //button2.setVisibility( View.VISIBLE );
        call = findViewById( R.id.callButton );
        viewFood.setVisibility( View.GONE );
        view2.setVisibility( View.GONE );
       // button2.setVisibility( View.GONE );



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

        // making call method
        call.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                String phone = "+25466777888";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
                startActivity( intent );


            }

        });


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
            //Cursor cursor= databaseHelper.getall(get_all, sqLiteDatabase);


                if (cursor.moveToFirst()) {
                    String FOOD = cursor.getString( 0 );
                    String D = cursor.getString( 1 );
                    String P=cursor.getString( 2 );
                    viewFood.setText( FOOD );
                    viewDe.setText( D );
                    viewPrices.setText(P);
                    viewFood.setVisibility( View.VISIBLE );
                    view2.setVisibility( View.VISIBLE );
                } else {
                    Toast.makeText( getBaseContext(), " Could not find a review, Please enter other desciption", Toast.LENGTH_LONG ).show();
                    DatabaseUtils.dumpCursorToString( cursor );
                    cursor.getPosition();

                    Log.v( "Cursor object", DatabaseUtils.dumpCursorToString( cursor ) );
                }

        }


        //save
    public  void savedFood(){
        Intent intent= new Intent(this, insert.class);
        startActivity(intent);
    }

    public  void viewFood(){
        Intent intent= new Intent(this, foods.class);
        startActivity(intent);
    }

}
