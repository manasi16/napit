package com.example.android.fitnessapp2;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

public class SVM extends Service {

//https://github.com/yctung/AndroidLibSVM
    public SVM() {
        String systemPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        String appFolderPath = systemPath + "libsvm/"; // your datasets folder
        //LibSVM svm = new LibSVM();

        //svm.scale(appFolderPath + "heart_scale", appFolderPath + "heart_scale_scaled");
        //svm.train("-t 2 "/* svm kernel */ + appFolderPath + "heart_scale_scaled " + appFolderPath + "model");
        //svm.predict(appFolderPath + "hear_scale_predict " + appFolderPath + "model " + appFolderPath + "result");
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

    }

    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return mStartMode;
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

    }
}
