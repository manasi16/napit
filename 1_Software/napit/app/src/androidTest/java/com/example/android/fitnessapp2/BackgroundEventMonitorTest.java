package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john grun on 12/14/17.
 * written by john grun
 */
public class BackgroundEventMonitorTest {

    BackgroundEventMonitor Test;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onBind() throws Exception {
    }

    @Test
    public void onCreate() throws Exception {
        // see if we can create the object
        BackgroundEventMonitor Test = new BackgroundEventMonitor();
        //onCreate();
        // test that the object is not null. Ie created successfully
        assertNotSame(nullValue(), Test);

    }

    @Test
    public void onDestroy() throws Exception {
    }

}