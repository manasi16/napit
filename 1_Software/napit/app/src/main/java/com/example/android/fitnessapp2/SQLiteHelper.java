package com.example.android.fitnessapp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static android.icu.text.MessagePattern.ArgType.SELECT;
import static android.os.Build.ID;

/**
 * Created by Aishwarya Srikanth on 11/3/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="UserDataBase";

        public static final String TABLE_NAME="UserTable";


        public static final String Table_Column_ID="id";


        public static final String Table_Column_1_Name="name";

        public static final String Table_Column_2_Email="email";

        public static final String Table_Column_3_Password="password";
    //ExerciseTable
        public static final String TABLE_NAME2 = "ExerciseTable";

        public static final String Ex_Col0= "Email";
        public static final String Ex_Col1 = "Steps";
        public static final String Ex_Col2 = "Miles";
        public static final String Ex_Col3 = "Calories";
        public static final String Ex_Col4 = "Duration";
        public static final String Ex_Col5 = "Date";
        public static final String Ex_ID = "Exercise_ID";


    //Personal Details Table
    public static final String TABLE_NAME_Details = "PersonalDetails";

    public static final String Table_Column_Details_ID="id";

    public static final String Table_Column_Age = "age";

    public static final String Table_Column_Weight = "weight";

    public static final String Table_Column_Height = "height";

    public static final String Table_Column_Location = "location";




    public SQLiteHelper(Context context) {

            super(context, DATABASE_NAME, null, 15);
}

        @Override
        public void onCreate(SQLiteDatabase database) {

            String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_Email+" VARCHAR, "+Table_Column_3_Password+" VARCHAR)";
            String CREATE_TABLE2="CREATE TABLE IF NOT EXISTS "+TABLE_NAME2+" ("+Ex_ID+" INTEGER PRIMARY KEY, "+Ex_Col0+" VARCHAR, "+Ex_Col1+" VARCHAR, "+Ex_Col2+" VARCHAR, "+Ex_Col3+" VARCHAR, "+Ex_Col4+" VARCHAR, "+Ex_Col5+" VARCHAR)";

            String CREATE_TABLEDetails = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_Details + " ("+ Table_Column_Details_ID + " INTEGER PRIMARY KEY, " + Table_Column_1_Name+" VARCHAR, "+ Table_Column_2_Email +" VARCHAR, " + Table_Column_Age + " REAL, " + Table_Column_Height + " REAL, " + Table_Column_Weight + " REAL, " + Table_Column_Location + " VARCHAR " + ");";

            database.execSQL(CREATE_TABLE);
            database.execSQL(CREATE_TABLE2);
            database.execSQL(CREATE_TABLEDetails);
            //String CREATE_TABLE2="CREATE TABLE IF NOT EXISTS "+TABLE_NAME2+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+xval+" VARCHAR, "+yval+" VARCHAR, "+zval+" VARCHAR)";
            //database.execSQL(CREATE_TABLE2);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

           //String drop="DROP TABLE IF EXISTS "+ TABLE_NAME2;
            //db.execSQL(drop);
           onCreate(db);

        }

    public long insertExercise(String email,float steps,float miles, float calories, String duration,String date)
    {
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Ex_Col0,email);
        contentValues.put(Ex_Col1,steps);
        contentValues.put(Ex_Col2,miles);
        contentValues.put(Ex_Col3,calories);
        contentValues.put(Ex_Col4,duration);
        contentValues.put(Ex_Col5,date);
        long id = sqLiteDatabase.insert(TABLE_NAME2,null,contentValues);
        return id;

    }

    public boolean getUser(String email, String pass)
    {
        String selectQuery= "SELECT * FROM " + TABLE_NAME + " where " + Table_Column_2_Email + " = " + "'"+email+"'" + " and " + Table_Column_3_Password + " = " + "'"+pass+"'";
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(selectQuery,null);
        cursor.moveToFirst();
        if(cursor.getCount()>0)
        {
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }



    public void addPersonalDetails(String name,String email,String age, String height, String weight,String location) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Table_Column_1_Name,name);
        values.put(Table_Column_2_Email,email);
        values.put(Table_Column_Age,age);
        values.put(Table_Column_Height,height);
        values.put(Table_Column_Weight,weight);
        values.put(Table_Column_Location,location);

        db.insert(TABLE_NAME_Details,null,values);
        db.close();
    }



    public Cursor getListData(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor data = sqLiteDatabase.rawQuery("Select * FROM "+ TABLE_NAME2,null);
        return data;
    }
    public Cursor getRecentEx(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor data = sqLiteDatabase.rawQuery("Select * FROM "+ TABLE_NAME2,null);
        data.moveToLast();
        return data;

    }

}