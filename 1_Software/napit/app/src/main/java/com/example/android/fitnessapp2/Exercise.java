package com.example.android.fitnessapp2;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Exercise extends AppCompatActivity implements SensorEventListener {
    SensorManager sm;
    TextView tv,displaydist;
    Button resetButton,getDist;
    public float stepsInSensor = 0;
    public float stepsAtReset;
    public float stepsSinceReset;
    boolean walk = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        stepsAtReset = prefs.getFloat("stepsAtReset", 0);
        tv=(TextView)findViewById(R.id.value);
        resetButton=(Button)findViewById(R.id.resetbutton);
        displaydist=(TextView)findViewById(R.id.displaydistance);
        getDist=(Button)findViewById(R.id.dist);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                float distance = (float)(stepsSinceReset*78)/(float)100000;
                displaydist.setText(String.valueOf(distance));

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


}
