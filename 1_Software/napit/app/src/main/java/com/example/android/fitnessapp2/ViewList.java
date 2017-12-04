package com.example.android.fitnessapp2;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewList extends AppCompatActivity {

    ListView lview;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        lview=(ListView)findViewById(R.id.viewlist);
        sqLiteHelper=new SQLiteHelper(this);
        final ArrayList<String> myList = new ArrayList<>();
        Cursor data = sqLiteHelper.getListData();
        if(data.getCount()==0){
            Toast.makeText(this,"Database is empty",Toast.LENGTH_SHORT).show();

        }
        else{
            while (data.moveToNext()){
                myList.add("Steps: "+data.getString(1)+"\nMiles: "+ data.getString(2)+"\nCalories: "+ data.getString(3)+"\nDuration: "+data.getString(4));
                //   myList.add(data.getString(2));



                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,myList);
                lview.setAdapter(listAdapter);
            }
        }



    }
}
