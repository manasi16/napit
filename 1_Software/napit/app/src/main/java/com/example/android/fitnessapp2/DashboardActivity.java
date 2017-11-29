package com.example.android.fitnessapp2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends AppCompatActivity {

ImageButton sleepImage, exerciseImage, foodImage, vsImage, sugImage, DebugImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sleepImage=(ImageButton) findViewById(R.id.sleepicon);
        exerciseImage=(ImageButton) findViewById(R.id.exerciseicon);
        foodImage=(ImageButton) findViewById(R.id.foodicon);
        vsImage=(ImageButton)findViewById(R.id.vsicon);
        sugImage=(ImageButton) findViewById(R.id.sugicon);

        DebugImage=(ImageButton) findViewById(R.id.sqldebug);

        sleepImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(DashboardActivity.this,SleepActivity.class);
                startActivity(i);
            }
        });



        DebugImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent dbmanager = new Intent(DashboardActivity.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });



    }
}
