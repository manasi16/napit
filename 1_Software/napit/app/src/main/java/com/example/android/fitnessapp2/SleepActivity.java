package com.example.android.fitnessapp2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SleepActivity extends Activity implements SensorEventListener {
    private TextView xText, yText, zText;
    private Sensor mySensor;
    Button startSleep,stopSleep;
    TextView startSleepTime,stopSleepTime;

    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Register sensor Listener
      //  SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        // Assign TextView
        xText = (TextView)findViewById(R.id.xText);
        yText = (TextView)findViewById(R.id.yText);
        zText = (TextView)findViewById(R.id.zText);
        startSleep=(Button)findViewById(R.id.startsleep);
        stopSleep=(Button)findViewById(R.id.stopbutton);
        startSleepTime=(TextView)findViewById(R.id.sleeptime1);
        stopSleepTime=(TextView)findViewById(R.id.sleeptime2);
        startSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
                String date=dfDate.format(Calendar.getInstance().getTime());
                DateFormat dfTime = new SimpleDateFormat("HH:mm");
                String time = dfTime.format(Calendar.getInstance().getTime());
                startSleepTime.setText(date + " " + time);
                switch (view.getId()) {
                    case R.id.startsleep:
                        SM.registerListener(SleepActivity.this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
                        break;
                    case R.id.stopbutton:
                        SM.unregisterListener(SleepActivity.this);
                        break;
                }
            }

        });

        stopSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
                String date=dfDate.format(Calendar.getInstance().getTime());
                DateFormat dfTime = new SimpleDateFormat("HH:mm");
                String time = dfTime.format(Calendar.getInstance().getTime());
                stopSleepTime.setText(date + " " + time);
                switch (view.getId()) {
                    case R.id.startsleep:
                        SM.registerListener(SleepActivity.this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
                        break;
                    case R.id.stopbutton:
                        SM.unregisterListener(SleepActivity.this);
                        break;

                }

            }
        });


        // series = new LineGraphSeries<DataPoint>();
    }
    protected void onResume() {
        super.onResume();
        SM.unregisterListener(this);
        //SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    protected void onPause() {
        super.onPause();
        SM.unregisterListener(this);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Not in use
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        xText.setText("X: " + event.values[0]);
        yText.setText("Y: " + event.values[1]);
        zText.setText("Z: " + event.values[2]);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean WriteSensorReadingsToDatabase(SensorEvent event)
    {

        return true;
    }





}
