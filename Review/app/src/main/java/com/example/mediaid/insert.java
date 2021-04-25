package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class insert extends AppCompatActivity {
    EditText foo, description, price;
    Context context= this;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert );
        foo=findViewById( R.id.foods );
        description=(EditText)findViewById( R.id.descriptionD );
        price=findViewById( R.id.price );




    }

    public  void  addFood( View view){
        String food=  foo.getText().toString();
        String desc= description.getText().toString();
        String prices= price.getText().toString();
        databaseHelper = new DatabaseHelper( context );
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        databaseHelper.insertD( food, desc,sqLiteDatabase );

        Toast.makeText( getBaseContext(), " Data Saved", Toast.LENGTH_LONG ).show();
        databaseHelper.close();

    }
}
