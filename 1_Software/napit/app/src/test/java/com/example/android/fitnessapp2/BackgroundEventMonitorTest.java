package com.example.android.fitnessapp2;

import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by  john grun on 12/12/17.
 */
public class BackgroundEventMonitorTest {
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