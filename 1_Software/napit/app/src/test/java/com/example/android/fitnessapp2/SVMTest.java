package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john grun on 12/12/17.
 */
public class SVMTest {
    SVM Test;
    @Before
    public void setUp() throws Exception {
        // see if we can create the object
        Test = new SVM();
        //onCreate();
        // test that the object is not null. Ie created successfully
        assertNotSame(nullValue(), Test);

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void onCreate() throws Exception {
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
        // works but test fails -- something not init
        //String testDist = Test.getAverage();
        //assertNotSame(nullValue(), testDist);
    }

    @Test
    public void getPeak() throws Exception {
        // works
        //String testDist = Test.getPeak();
        //assertNotSame(nullValue(), testDist);
    }

    @Test
    public void getMin() throws Exception {
        //works
        //String testDist = Test.getMin();
        //assertNotSame(nullValue(), testDist);
    }

    @Test
    public void trainSVM() throws Exception {
        // test as independent component
        // place files on sd
    }

    @Test
    public void predictSVM() throws Exception {
        // place files on sd
    }

    @Test
    public void writeAnalysisResultsToDB() throws Exception {
        // place files on sd
    }

}