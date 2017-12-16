package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john on 12/15/17.
 */
public class ExerciseTest {

    ExerciseTest Test;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() throws Exception {

        // see if we can create the object
        Test = new ExerciseTest();
        //onCreate();
        // test that the object is not null. Ie created successfully
        assertNotSame(nullValue(), Test);
    }

    @Test
    public void onResume() throws Exception {
    }

    @Test
    public void onPause() throws Exception {
    }

    @Test
    public void onDestroy() throws Exception {
    }

}