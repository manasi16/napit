package com.example.android.fitnessapp2;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

import android.os.IBinder;

import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;


public class StepCounter extends Service implements SensorEventListener  {

    //global for this service
    public float stepsInSensor = 0;
    public float stepsAtReset;
    public float stepsSinceReset,miles,calories;
    boolean walk = false;
    SharedPreferences prefs;

    SensorManager sm; // sensor object

    public StepCounter() {

    }

    /** indicates how to behave if the service is killed */
    int mStartMode;

    /** interface for clients that bind */
    IBinder mBinder;

    /** indicates whether onRebind should be used */
    boolean mAllowRebind;

    /** Called when the service is being created. */
    @Override
    public void onCreate() {
        super.onCreate();
        prefs  = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        stepsAtReset = prefs.getFloat("stepsAtReset", 0);
        sm=(SensorManager)getSystemService(Context.SENSOR_SERVICE);

        Toast.makeText(this,"Starting step counter", Toast.LENGTH_LONG).show();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setTicker("STEPCOUNTER").setContentTitle("STEPCOUNTER").setContentText("STEPCOUNTER")
                //.setWhen(System.currentTimeMillis()).setAutoCancel(false)
                .setOngoing(true).setPriority(Notification.PRIORITY_HIGH);
                //.setContentIntent(pendIntent);
        Notification notification = builder.build();

        int id = (int)System.currentTimeMillis()%10000;
        startForeground(id, notification);
    }

    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        stepsAtReset = stepsInSensor;
        //stopSelf();
        //super.onResume();
        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        editor.putFloat("stepsAtReset", stepsAtReset);
        editor.commit();
        walk=true;
        Sensor countSensor = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if(countSensor != null)
        {
            sm.registerListener(this,countSensor,sm.SENSOR_DELAY_UI);

        }
        else
        {
            Toast.makeText(this,"Sensor Not Found!",Toast.LENGTH_SHORT).show();

        }

        return super.onStartCommand(intent,flags,startId);

    }

    public void onSensorChanged(SensorEvent sensorEvent) {
       //if(walk)
       // {
            stepsInSensor = sensorEvent.values[0];
            stepsSinceReset = stepsInSensor - stepsAtReset;
            Intent RTReturn = new Intent(Exercise.RECEIVE_Count);
            RTReturn.putExtra("stepcount",stepsSinceReset);
            LocalBroadcastManager.getInstance(this).sendBroadcast(RTReturn);

        //}
        //else
        //{
        //    sensorEvent.values[0]=0;
        //}

    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }


    /** Called when all clients have unbound with unbindService() */
    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

    /** Called when a client is binding to the service with bindService()*/
    @Override
    public void onRebind(Intent intent) {

    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"Stopping step counter", Toast.LENGTH_LONG).show();
        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        editor.putFloat("stepsAtReset", stepsAtReset);
        editor.commit();
    }

}