package com.example.android.fitnessapp2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Exercise extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    Chronometer simpleChronometer;
    TextView tv,displaydist,displayMiles,displayCalories;
    Button getDist,getMiles,getCalories,startButton,stopButton,restartButton,btnLogout;
    public float stepsInSensor = 0;
    public float stepsAtReset;
    public float stepsSinceReset,miles,calories;
    String duration,email1,date1,date;
    private Session session;
    SQLiteHelper Exercisedb;



    boolean walk = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);

        stepsAtReset = prefs.getFloat("stepsAtReset", 0);
        Exercisedb = new SQLiteHelper(this);
        simpleChronometer = (Chronometer) findViewById(R.id.simpleChronometer);
        tv=(TextView)findViewById(R.id.value);
        restartButton=(Button)findViewById(R.id.restartex);


        displayMiles=(TextView)findViewById(R.id.displaymiles);
        displayCalories=(TextView)findViewById(R.id.displaycals);
        getMiles=(Button)findViewById(R.id.getmiles);
        getCalories=(Button)findViewById(R.id.getcals);
        startButton=(Button)findViewById(R.id.startex);
        stopButton=(Button)findViewById(R.id.stopex);

        displaydist=(TextView)findViewById(R.id.displaydistance);
        getDist=(Button)findViewById(R.id.dist);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        session= new Session(this);
        String email= session.getEmail();
        email1=email.toString();
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


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepsAtReset = stepsInSensor;

                SharedPreferences.Editor editor =
                        getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                editor.putFloat("stepsAtReset", stepsAtReset);
                editor.commit();

                // you can now display 0:
                tv.setText(String.valueOf(0));
                simpleChronometer.start();


            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                simpleChronometer.stop();
                duration = simpleChronometer.getText().toString();





            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleChronometer.setBase(SystemClock.elapsedRealtime());
                stepsAtReset = stepsInSensor;

                SharedPreferences.Editor editor =
                        getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
                editor.putFloat("stepsAtReset", stepsAtReset);
                editor.commit();

                // you can now display 0:
                tv.setText(String.valueOf(0));

            }
        });

        getDist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  float distance = (float)(stepsSinceReset*78)/(float)100000;
                //displaydist.setText(String.valueOf(distance));
                Intent intent = new Intent(Exercise.this,ViewList.class);
                startActivity(intent);

            }
        });
        getMiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                miles = (float)(stepsSinceReset*0.0005);
                displayMiles.setText(String.valueOf(miles));
            }
        });
        getCalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calories = (float)(miles*0.0128);
                displayCalories.setText(String.valueOf(calories));
            }
        });





    }






    @Override
    protected void onResume() {
        super.onResume();
        walk=true;
        Sensor countSensor = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null){
            sm.registerListener(this,countSensor,sm.SENSOR_DELAY_UI);

        }
        else
        {
            Toast.makeText(this,"Sensor Not Found!",Toast.LENGTH_SHORT).show();

        }
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        walk=false;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(walk)
        {
            stepsInSensor = sensorEvent.values[0];
            stepsSinceReset = stepsInSensor - stepsAtReset;
            tv.setText(String.valueOf(stepsSinceReset));

        }
        else
        {
            sensorEvent.values[0]=0;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void insertdetails(View v){
        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        date=dfDate.format(Calendar.getInstance().getTime());
        date1=date.toString();
        DateFormat dfTime = new SimpleDateFormat("HH:mm");

        long id = Exercisedb.insertExercise(email1,stepsSinceReset,miles,calories,duration,date1);
        Toast.makeText(this,"Let's check values",Toast.LENGTH_SHORT).show();

        if(id<0){
            Toast.makeText(this,"Transferring to database failed",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Transfer Successful",Toast.LENGTH_SHORT).show();
        }

    }


    private void logout() {
        session.setLoggedin(false);
        finish();
        startActivity(new Intent(Exercise.this, MainActivity.class));
    }


}
