package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john grun on 12/12/17.
 */
public class DashboardActivityTest {
    DashboardActivityTest Test;
    @Before
    public void setUp() throws Exception {
        Test = new DashboardActivityTest();
        assertNotSame(nullValue(), Test);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() throws Exception {
    }

}