package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john grun on 12/12/17.
 */
public class SQLiteHelperTest {
    SQLiteHelperTest Test;
    @Before
    public void setUp() throws Exception {
        // see if we can create the object
        Test = new SQLiteHelperTest();
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
    public void onUpgrade() throws Exception {
    }

    @Test
    public void addSensorReading() throws Exception {

    }

    @Test
    public void getSensorReading() throws Exception {

        //getSensorReading();
    }

    @Test
    public void insertExercise() throws Exception {
    }

    @Test
    public void getUser() throws Exception {
    }

    @Test
    public void addPersonalDetails() throws Exception {
    }

    @Test
    public void getProfileData() throws Exception {
    }

    @Test
    public void getListData() throws Exception {
    }

    @Test
    public void getRecentEx() throws Exception {
    }

    @Test
    public void getLastRow() throws Exception {
    }

    @Test
    public void getLastRowStep() throws Exception {
    }

    @Test
    public void getLastRowResult() throws Exception {
    }

    @Test
    public void getReadingsByDate() throws Exception {
    }

    @Test
    public void getSensorReadingsByDate() throws Exception {
    }

    @Test
    public void getAllSensorReadings() throws Exception {
    }

    @Test
    public void getSensorReadingCount() throws Exception {
    }

    @Test
    public void getData() throws Exception {
    }

    @Test
    public void addLibSVM_Output() throws Exception {
    }

    @Test
    public void addSVMOutput() throws Exception {
    }

    @Test
    public void getRecentSleep() throws Exception {
    }

    @Test
    public void getSleepResultData() throws Exception {

    }

}