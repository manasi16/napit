package com.example.android.fitnessapp2;

import android.hardware.SensorEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john grun on 12/12/17.
 */
public class StepCounterTest {

    StepCounter Test;
    @Before
    public void setUp() throws Exception {

        // see if we can create the object
        Test = new StepCounter();
        //onCreate();
        // test that the object is not null. Ie created successfully
        assertNotSame(nullValue(), Test);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() throws Exception {
        //just create object
    }

    @Test
    public void onBind() throws Exception {
    }

    @Test
    public void onStartCommand() throws Exception {

    }

    @Test
    public void getDistance() throws Exception {
        float testDist = Test.getDistance();
        assertNotSame(-1, testDist);

    }

    @Test
    public void getMiles() throws Exception {
        float testDist = Test.getDistance();
        assertNotSame(-1, testDist);
    }

    @Test
    public void getCalories() throws Exception {
        float testDist = Test.getCalories();
        assertNotSame(-1, testDist);
    }

    @Test
    public void getDuration() throws Exception {
        String testDist = Test.getDuration();
        assertNotSame(nullValue(), testDist);
    }

    @Test
    public void onSensorChanged() throws Exception {
        //onSensorChanged(); // have to have sensor object
    }

    @Test
    public void insertdetails() throws Exception {
        // does nothing
    }

    @Test
    public void onAccuracyChanged() throws Exception {
        //does nothing
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

}