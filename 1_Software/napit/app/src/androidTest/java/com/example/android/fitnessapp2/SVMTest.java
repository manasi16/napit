package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john on 12/15/17.
 */
public class SVMTest {
    SVM Test;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() throws Exception {
        // see if we can create the object
        Test = new SVM();
        //onCreate();
        // test that the object is not null. Ie created successfully
        assertNotSame(nullValue(), Test);
    }

    @Test
    public void onBind() throws Exception {
    }

    @Test
    public void onStartCommand() throws Exception {
    }

    @Test
    public void onUnbind() throws Exception {
    }

    @Test
    public void onRebind() throws Exception {
    }

    @Test
    public void onDestroy() throws Exception {
    }

    @Test
    public void getDataFromDatabase() throws Exception {
    }

    @Test
    public void getAverage() throws Exception {
    }

    @Test
    public void getAverageForStdDev() throws Exception {
    }

    @Test
    public void getPeak() throws Exception {
    }

    @Test
    public void getMin() throws Exception {
    }

    @Test
    public void getStdDev() throws Exception {
    }

    @Test
    public void trainSVM() throws Exception {
    }

    @Test
    public void predictSVM() throws Exception {
    }

    @Test
    public void writeAnalysisResultsToDB() throws Exception {
    }

}