package com.example.android.fitnessapp2;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import umich.cse.yctung.androidlibsvm.LibSVM;

public class SVM extends Service {

//https://github.com/yctung/AndroidLibSVM
    //global for this service
    static final String LOG_TAG = "LibSVM";
    String systemPath;
    String appFolderPath;
    LibSVM svm;

    public SVM() {
        /*systemPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        appFolderPath = systemPath + "libsvm_Example_Datasets/"; // your datasets folder
        svm = new LibSVM();*/
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

        systemPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
        appFolderPath = systemPath + "libsvm/"; // your datasets folder

        //Create necessary folders to save model files
        CreateAppFolderIfNeeded();
        copyAssetsDataIfNeed();

        //Assign model/output paths
        /*String dataTrainPath = appFolderPath+"heart_scale ";
        String dataScaledPath = appFolderPath+"scaled";
        String dataPredictPath = appFolderPath+"heart_scale ";
        String modelPath = appFolderPath+"model ";
        String outputPath = appFolderPath+"predict ";*/

        svm = new LibSVM();
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
        Toast.makeText(this, "Training Data!", Toast.LENGTH_SHORT).show();
        //svm.scale(appFolderPath + "heart_scale ", appFolderPath + "heart_scale_scaled");
        //svm.train("-t 2 "/* svm kernel */ + appFolderPath + "heart_scale_scaled " + appFolderPath + "model");;

        svm.scale(appFolderPath + "heart_scale ", appFolderPath + "scaled");
        svm.train("-t 2 "/*svm kernel*/ + appFolderPath + "heart_scale "+ appFolderPath + "model ");
    }

    public void PredictSVM()
    { // try to predict something
        Toast.makeText(this,"Analysing Data", Toast.LENGTH_LONG).show();
        //svm.predict(appFolderPath + "hear_scale_predict " + appFolderPath + "model " + appFolderPath + "result");
        svm.predict(appFolderPath + "heart_scale_predict "+ appFolderPath+"model " + appFolderPath + "predict ");
    }

    public void WriteAnalysisResultsToDB()
    { // whatever format this puts out
        // store into the database with username and date.

    }

    private void CreateAppFolderIfNeeded(){
        File folder = new File(appFolderPath);

        if (!folder.exists()) {
            folder.mkdir();
            Log.d(LOG_TAG,"Appfolder is not existed, create one");
        } else {
            Log.w(LOG_TAG,"WARN: Appfolder has not been deleted");
        }

    }

    private void copyAssetsDataIfNeed() {
        String assetsToCopy[] = {"heart_scale_predict","heart_scale_train","heart_scale"};
        //String targetPath[] = {C.systemPath+C.INPUT_FOLDER+C.INPUT_PREFIX+AudioConfigManager.inputConfigTrain+".wav", C.systemPath+C.INPUT_FOLDER+C.INPUT_PREFIX+AudioConfigManager.inputConfigPredict+".wav",C.systemPath+C.INPUT_FOLDER+"SomeoneLikeYouShort.mp3"};

        for(int i=0; i<assetsToCopy.length; i++){
            String from = assetsToCopy[i];
            String to = appFolderPath+from;

            // 1. check if file exist
            File file = new File(to);
            if(file.exists()){
                Log.d(LOG_TAG, "copyAssetsDataIfNeed: file exist, no need to copy:"+from);
            } else {
                // do copy
                boolean copyResult = copyAsset(getAssets(), from, to);
                Log.d(LOG_TAG, "copyAssetsDataIfNeed: copy result = "+copyResult+" of file = "+from);
            }
        }
    }

    private boolean copyAsset(AssetManager assetManager, String fromAssetPath, String toPath) {

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(fromAssetPath);
            new File(toPath).createNewFile();
            out = new FileOutputStream(toPath);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "[ERROR]: copyAsset: unable to copy file = "+fromAssetPath);
            return false;
        }
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }
}
