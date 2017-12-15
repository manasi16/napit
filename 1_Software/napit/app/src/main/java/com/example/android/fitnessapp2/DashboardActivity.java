package com.example.android.fitnessapp2;
// written by Aishwarya Srikanth, John Grun
// tDebugging Aishwarya Srikanth, John Grun
// Testing Aishwarya Srikanth, John Grun
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

    ImageButton sleepImage, exerciseImage, contactImage, vsImage, sugImage, faqImage,DebugImage;
    Button btnLogout;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sleepImage=(ImageButton) findViewById(R.id.sleepicon);
        exerciseImage=(ImageButton) findViewById(R.id.exerciseicon);
        contactImage=(ImageButton) findViewById(R.id.contacticon);
        DebugImage=(ImageButton) findViewById(R.id.sqldebug);
        vsImage=(ImageButton)findViewById(R.id.vsicon);
        sugImage=(ImageButton) findViewById(R.id.suggestionicon);
        faqImage=(ImageButton) findViewById(R.id.faqicon);

        //session
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
        sugImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardActivity.this,Suggestions.class);
                startActivity(i);
            }
        });
        DebugImage.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent dbmanager = new Intent(DashboardActivity.this,AndroidDatabaseManager.class);
                startActivity(dbmanager);
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

        faqImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent(DashboardActivity.this, FAQ.class);
                startActivity(i2);
            }
        });

        contactImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent i2= new Intent(DashboardActivity.this, ContactUs.class);
                    startActivity(i2);
                }

        });



    }
    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(DashboardActivity.this, MainActivity.class));
    }


}
