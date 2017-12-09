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

ImageButton sleepImage, exerciseImage, foodImage, vsImage, sugImage,settingsImage,DebugImage;
    Button btnLogout;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sleepImage=(ImageButton) findViewById(R.id.sleepicon);
        settingsImage=(ImageButton)findViewById(R.id.profile);
        exerciseImage=(ImageButton) findViewById(R.id.exerciseicon);
        foodImage=(ImageButton) findViewById(R.id.foodicon);
        vsImage=(ImageButton)findViewById(R.id.vsicon);
        sugImage=(ImageButton) findViewById(R.id.sugicon);
        DebugImage=(ImageButton) findViewById(R.id.sqldebug);
        session= new Session(this);
        String email1= session.getEmail();
        Toast.makeText(this, email1, Toast.LENGTH_LONG).show();

        if(!session.loggedin())
        {
            logout();
        }
        btnLogout=(Button)findViewById(R.id.idLogOut);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }

        });
        settingsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this,PersonalDetails.class);
                startActivity(i);
            }
        });


        sleepImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i = new Intent(DashboardActivity.this,SleepActivity.class);
                startActivity(i);
            }
        });
        exerciseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(DashboardActivity.this,Exercise.class);
                startActivity(i1);
            }
        });
        vsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(DashboardActivity.this,ViewExercise.class);
                startActivity(i1);

            }
        });
        DebugImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent dbmanager = new Intent(DashboardActivity.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
            }
        });
      sugImage.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i1 = new Intent(DashboardActivity.this,ContactUs.class);
              startActivity(i1);
          }
      });


    }
    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(DashboardActivity.this, MainActivity.class));
    }

}
