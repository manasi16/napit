package com.example.android.fitnessapp2;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SleepActivity extends Activity implements SensorEventListener {
    private TextView xText, yText, zText;
    private Sensor mySensor;
    Button startSleep,stopSleep, train_svm;
    TextView startSleepTime,stopSleepTime;
    String date, date1, time, time1;

    private SensorManager SM;
    SQLiteHelper SensorReadingdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        // Create our Sensor Manager
        SM = (SensorManager)getSystemService(SENSOR_SERVICE);

        // Accelerometer Sensor
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        SensorReadingdb = new SQLiteHelper(this);

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
        train_svm = (Button)findViewById(R.id.Train_SVM);
        startSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
                String date=dfDate.format(Calendar.getInstance().getTime());
                DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
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
                DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
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

        /*train_svm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Train the SVM
                Toast.makeText(getApplicationContext(), "Train_SVM called!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SleepActivity.this,SVM.class);
                i.putExtra("Train",true);
                Toast.makeText(getApplicationContext(), "Service called!", Toast.LENGTH_SHORT).show();
                startService(i);
            }
        });*/


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

        //DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
        //Getting date and time

        DateFormat dfDate = new SimpleDateFormat("yyyy/MM/dd");
        date=dfDate.format(Calendar.getInstance().getTime());
        date1=date.toString();
        DateFormat dfTime = new SimpleDateFormat("HH:mm:ss");
        time=dfTime.format(Calendar.getInstance().getTime());
        time1=time.toString();

        SensorReadingdb.addSensorReading(event.values[2], date1, time1);

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

    public void Run_Analysis(View v)
    { // Run the analysis on the data
        Intent i = new Intent(this,SVM.class);
        startService(i);
    }

    public void Train_SVM(View v)
    { // Train the SVM

        Toast.makeText(this, "Train_SVM called!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,SVM.class);
        i.putExtra("Train",true);
        Toast.makeText(this, "Service called!", Toast.LENGTH_SHORT).show();
        startService(i);
        /*
        Intent i = new Intent(SleepActivity.this,DisplayReading.class);
        startActivity(i);*/
    }



}



