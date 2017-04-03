package com.example.supervisor.sqlitecrudexample;

import android.database.Cursor;
import android.database.sqlite.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String dbName = "AndroidDatabase";
    private final String tableName = "Versions";
    private final String[] versionNames = new String[]{"Cupcake", "Donut", "Eclair"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> results = new ArrayList<String>();
        //Declare SQLiteDatabase object
        SQLiteDatabase sampleDB = null;

        try {
            //Instantiate sampleDB object
            sampleDB =  this.openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            //Create table using execSQL
            //sampleDB.execSQL("CREATE TABLE IF NOT EXISTS " + tableName +    " (versionname VARCHAR);");
            //Insert Android versions into table created
            for(String ver: versionNames){
                sampleDB.execSQL("INSERT INTO " + tableName + " Values ('"+ver+"');");
            }

            //Create Cursor object to read versions from the table
            Cursor c = sampleDB.rawQuery("SELECT versionname FROM " + tableName, null);
            //If Cursor is valid
            if (c != null ) {
                //Move cursor to first row
                if  (c.moveToFirst()) {
                    do {
                        //Get version from Cursor
                        String firstName = c.getString(c.getColumnIndex("versionname"));
                        //Add the version to Arraylist 'results'
                        results.add(firstName);
                    }while (c.moveToNext()); //Move to next row
                }

                for(int i=0; i<results.size(); i++){
                    System.out.println("hola = "+results.get(i));
                }

            }


        } catch (SQLiteException se ) {
            Toast.makeText(getApplicationContext(), "Couldn't create or open the database", Toast.LENGTH_LONG).show();
        } finally {
            if (sampleDB != null) {
                //sampleDB.execSQL("DELETE FROM " + tableName);
                sampleDB.close();
            }
        }

    }
}
