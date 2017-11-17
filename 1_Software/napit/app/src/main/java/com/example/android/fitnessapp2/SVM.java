package com.example.android.fitnessapp2;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import umich.cse.yctung.androidlibsvm.LibSVM;

public class SVM extends Service {

//https://github.com/yctung/AndroidLibSVM
    //global for this service
    String systemPath;
    String appFolderPath;
    LibSVM svm;

    public SVM() {
        systemPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        appFolderPath = systemPath + "libsvm/"; // your datasets folder
        svm = new LibSVM();
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
        Toast.makeText(this,"Pulling data from database", Toast.LENGTH_LONG).show();
    }

    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        boolean TrainMode = intent.getBooleanExtra("Train",false);

        // need to pass in a date to examine
        GetDataFromDatabase();

        if(TrainMode)
        {
            TrainSVM();
        }
        else
        {
            PredictSVM();
        }

        //return mStartMode;
        stopSelf();
        return super.onStartCommand(intent,flags,startId);

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
        Toast.makeText(this,"Finished", Toast.LENGTH_LONG).show();
    }

    public void GetDataFromDatabase()
    { // Pull data for given night

        // Convert to weird sparse matrix format see files under napit/libsvm_Example_Datasets
        // https://www.csie.ntu.edu.tw/~cjlin/libsvm/faq.html#/Q3:_Data_preparation
        // Also // //https://github.com/yctung/AndroidLibSVM

        // write to file at under appFolderPath
        // since this SVM only seems to like files.

    }

    public void TrainSVM()
    { // train the SVM with some data
        svm.scale(appFolderPath + "heart_scale", appFolderPath + "heart_scale_scaled");
        svm.train("-t 2 "/* svm kernel */ + appFolderPath + "heart_scale_scaled " + appFolderPath + "model");
    }

    public void PredictSVM()
    { // try to predict something
        Toast.makeText(this,"Analysing Data", Toast.LENGTH_LONG).show();
        svm.predict(appFolderPath + "hear_scale_predict " + appFolderPath + "model " + appFolderPath + "result");
    }

    public void WriteAnalysisResultsToDB()
    { // whatever format this puts out
        // store into the database with username and date.
    }


}
