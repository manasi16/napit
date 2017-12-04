package com.example.android.fitnessapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Aishwarya Srikanth on 11/3/2017.
 * Modified by John Grun 11/23/2017
 * Modified by Madhura on 11/30/2017
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="UserDataBase";

        public static final String TABLE_NAME="UserTable";

        public static final String Table_Column_ID="id";

        public static final String Table_Column_1_Name="name";

        public static final String Table_Column_2_Email="email";

        public static final String Table_Column_3_Password="password";

        ///Sensor table
        public static final String TABLE_NAME_Sensor="SensorTable";

        public static final String Table_Column_Sensor_ID="id";

        public static final String Table_Column_Sensor_Reading = "Sensor_Reading";

        public static final String Tables_Column_Sensor_Timestamp = "SensorTimestamp";

        //Sleep Anomaly Result table
        public static final String TABLE_NAME_AnomalyResult = "AnomalyResultTable";

        public static final String Table_Column_Anomaly_ID = "id";

        public static final String Table_Column_User_Email = "UserEmail";

        public static final String Table_Column_Date = "date";

        public static final String Table_Column_Result = "result";

        //Normal sleep: +1, Abnormal Sleep: -1
        public static final String Table_Column_Decision = "decision";

        //Don't care about this right now
        //public static final String Table_Column_1_Username="name";

        public SQLiteHelper(Context context) {

            super(context, DATABASE_NAME, null, 7);
}

        @Override
        public void onCreate(SQLiteDatabase database) {

            String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR)";
            //sensor table
            String CREATE_TABLESensor="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_Sensor+" ("+Table_Column_Sensor_ID+" INTEGER PRIMARY KEY, "+Table_Column_Sensor_Reading+" REAL, " + Tables_Column_Sensor_Timestamp+ " DATETIME DEFAULT (datetime('now','localtime')));";
            //sleep anomaly result table
            String CREATE_TABLEAnomaly="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_AnomalyResult+" ("+Table_Column_Anomaly_ID+" INTEGER PRIMARY KEY, "+/*Table_Column_User_Email+" VARCHAR, " + Table_Column_Date+ " DATETIME "+*/Table_Column_Result+ " REAL);";

            database.execSQL(CREATE_TABLE);
            database.execSQL(CREATE_TABLESensor);
            database.execSQL(CREATE_TABLEAnomaly);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_Sensor);
            onCreate(db);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_AnomalyResult);
            onCreate(db);

        }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addSensorReading(float SensorReading) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Table_Column_Sensor_Reading, SensorReading); // Contact Name

        // Inserting Row
        db.insert(TABLE_NAME_Sensor, null, values);
        db.close(); // Closing database connection
    }


    // Getting single sensor reading
    float getSensorReading(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME_Sensor, new String[] { Table_Column_Sensor_ID,
                        Table_Column_Sensor_Reading, Tables_Column_Sensor_Timestamp }, Table_Column_Sensor_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        float SensorReading = Float.parseFloat(cursor.getString(1));
        // return SensorReading
        return SensorReading;
    }

    // Getting All sensor readings
    public Vector getAllSensorReadings() {
        //List<float> contactList = new ArrayList<float>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_Sensor;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        Vector SensorReadings = new Vector();
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SensorReadings.addElement(Float.parseFloat(cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        // return contact list
        return SensorReadings;
    }
/*
    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }*/

/*    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }*/

    // Getting contacts Count
    public int getSensorReadingCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME_Sensor;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    public ArrayList<Cursor> getData(String Query){
        //get writable database
        SQLiteDatabase sqlDB = this.getWritableDatabase();
        String[] columns = new String[] { "message" };
        //an array list of cursor to save two cursors one has results from the query
        //other cursor stores error message if any errors are triggered
        ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
        MatrixCursor Cursor2= new MatrixCursor(columns);
        alc.add(null);
        alc.add(null);

        try{
            String maxQuery = Query ;
            //execute the query results will be save in Cursor c
            Cursor c = sqlDB.rawQuery(maxQuery, null);

            //add value to cursor2
            Cursor2.addRow(new Object[] { "Success" });

            alc.set(1,Cursor2);
            if (null != c && c.getCount() > 0) {

                alc.set(0,c);
                c.moveToFirst();

                return alc ;
            }
            return alc;
        } catch(SQLException sqlEx){
            Log.d("printing exception", sqlEx.getMessage());
            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+sqlEx.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        } catch(Exception ex){
            Log.d("printing exception", ex.getMessage());

            //if any exceptions are triggered save the error message to cursor an return the arraylist
            Cursor2.addRow(new Object[] { ""+ex.getMessage() });
            alc.set(1,Cursor2);
            return alc;
        }
    }

    //Adding the output of LibSVM to the database
    void addLibSVM_Output(int output) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Table_Column_Result, output); // Contact Name

        // Inserting Row
        db.insert(TABLE_NAME_AnomalyResult, null, values);
        db.close(); // Closing database connection
    }

}