package com.example.android.fitnessapp2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

/**
 * Created by john on 12/15/17.
 */
public class RegisterActivityTest {
    RegisterActivityTest Test;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void onCreate() throws Exception {
        // see if we can create the object
        Test = new RegisterActivityTest();
        //onCreate();
        // test that the object is not null. Ie created successfully
        assertNotSame(nullValue(), Test);
    }

    @Test
    public void SQLiteDataBaseBuild() throws Exception {
    }

    @Test
    public void SQLiteTableBuild() throws Exception {
    }

    @Test
    public void insertDataIntoSQLiteDatabase() throws Exception {
    }

    @Test
    public void emptyEditTextAfterDataInsert() throws Exception {
    }

    @Test
    public void checkEditTextStatus() throws Exception {
    }

    @Test
    public void checkingEmailAlreadyExistsOrNot() throws Exception {
    }

    @Test
    public void checkFinalResult() throws Exception {
    }

}