package edu.gatech.seclass.jobcompare6300;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.gatech.seclass.jobcompare6300.model.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;


import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;



public class DBTest {

    private DataBaseHelper freshDB;
    private DataBaseHelper db;

    @Before
    public void dbInitializer(){
        Context app = ApplicationProvider.getApplicationContext();
        freshDB = new DataBaseHelper(app);
    }
    @After
    public void deleteAll(){
        freshDB.getReadableDatabase().delete(DataBaseHelper.JOB_TABLE,null,null);
        //freshDB.getReadableDatabase().delete(DataBaseHelper.WEIGHT_JOB_TABLE,null,null);
        Context app = ApplicationProvider.getApplicationContext();
        app.deleteDatabase("jobcompare6300.db");
    }


    @Test
    public void appContextCheck(){
        Context app = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("edu.gatech.seclass.jobcompare6300",app.getPackageName());
    }

    @Test
    public void insertingTest(){
        Job newJob = new Job(100, "DataScientist","ab Corp","Seattle",
                2,100000,2000,100,2500,
                24);
        Boolean insertFlag = freshDB.addOne(newJob,false);
        assertEquals(true,
                insertFlag);
        Job queryResult = freshDB.getJob(100);

        assertNotNull(queryResult);
    }

    @Test
    public void currentJobCheck(){
        Context app = ApplicationProvider.getApplicationContext();
        Job newCurrentJob = new Job(200, "DataScientist","ab Corp","Seattle",
                2,100000,2000,100,2500,
                24);
       // freshDB.updateCurrentJob(newCurrentJob);
        Boolean insertFlag = freshDB.addOne(newCurrentJob,true);
        assertEquals(true,
                insertFlag);
        Job queryResult = freshDB.getJob(200);

        assertNotNull(queryResult);
        queryResult.updateCurrentJobStatus(app);
        assertEquals(true,
                queryResult.isCurrentJob());
    }

    @Test
    public void testAllJobs(){
        Job newJob1 = new Job(100, "DataScientist","ab Corp","Seattle",
                2,100000,2000,100,2500,
                24);
        Job newJob2 = new Job(200, "DataScientist","ab Corp","Seattle",
                2,100000,2000,100,2500,
                24);
        Job newJob3 = new Job(300, "DataScientist","ab Corp","Seattle",
                2,100000,2000,100,2500,
                24);
        Job newJob4 = new Job(400, "DataScientist","ab Corp","Seattle",
                2,100000,2000,100,2500,
                24);

        Boolean insertFlag1 = freshDB.addOne(newJob1,false);
        Boolean insertFlag2 = freshDB.addOne(newJob2,false);
        Boolean insertFlag3 = freshDB.addOne(newJob3,false);
        Boolean insertFlag4 = freshDB.addOne(newJob4,false);
        assertEquals(true,
                insertFlag1);
        assertEquals(true,
                insertFlag2);
        assertEquals(true,
                insertFlag3);
        assertEquals(true,
                insertFlag4);


        List allTogether = freshDB.getEveryone();

        assertNotNull(allTogether);
        assertEquals(4,allTogether.size());

    }
    @Test
    public void checkDefaultWeights(){
        freshDB.insertInitialWeights();
        assertNotNull(freshDB);
        SQLiteDatabase db = freshDB.getReadableDatabase();
        String queryString = "SELECT * FROM " + DataBaseHelper.WEIGHT_JOB_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor != null)
            cursor.moveToFirst();
        int salary_weight = cursor.getInt(1);
        int bonus_weight = cursor.getInt(2);
        int rsu_weight = cursor.getInt(3);
        int relocation_weight = cursor.getInt(4);
        int holiday_weight = cursor.getInt(5);
        //String query = "SELECT " + DataBaseHelper.WEIGHT_COLUMN_ID + ","+DataBaseHelper.WEIGHT_COLUMN_SALARY+ ","+DataBaseHelper.WEIGHT_COLUMN_BONUS+ ","+DataBaseHelper.WEIGHT_COLUMN_RSU+ ","+WEIGHT_COLUMN_RELOCATESTIPEND+","+WEIGHT_COLUMN_HOLIDAY+" FROM " + WEIGHT_JOB_TABLE;
        assertEquals(1, salary_weight);
        assertEquals(1,bonus_weight);
        assertEquals(1,rsu_weight);
        assertEquals(1,relocation_weight);
        assertEquals(1,holiday_weight);
    }

    @Test
    public void checkUpdatedWeights(){
        freshDB.insertInitialWeights();
        freshDB.updateWeights("2","3","5","6","4");
        assertNotNull(freshDB);
        SQLiteDatabase db = freshDB.getReadableDatabase();
        String queryString = "SELECT * FROM " + DataBaseHelper.WEIGHT_JOB_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor != null)
            cursor.moveToFirst();
        int salary_weight = cursor.getInt(1);
        int bonus_weight = cursor.getInt(2);
        int rsu_weight = cursor.getInt(3);
        int relocation_weight = cursor.getInt(4);
        int holiday_weight = cursor.getInt(5);
        //String query = "SELECT " + DataBaseHelper.WEIGHT_COLUMN_ID + ","+DataBaseHelper.WEIGHT_COLUMN_SALARY+ ","+DataBaseHelper.WEIGHT_COLUMN_BONUS+ ","+DataBaseHelper.WEIGHT_COLUMN_RSU+ ","+WEIGHT_COLUMN_RELOCATESTIPEND+","+WEIGHT_COLUMN_HOLIDAY+" FROM " + WEIGHT_JOB_TABLE;
        assertEquals(2, salary_weight);
        assertEquals(3,bonus_weight);
        assertEquals(5,rsu_weight);
        assertEquals(6,relocation_weight);
        assertEquals(4,holiday_weight);
    }
    @Test
    public void testJobScore() {
        Job newJob1 = new Job(100, "DataScientist", "ab Corp", "Seattle",
                2, 100000, 2000, 100, 2500,
                24);
        freshDB.addOne(newJob1, false);
        Weight.setBonusWeight(1);
        Weight.setHolidaysWeight(1);
        Weight.setRsuWeight(1);
        Weight.setRelocationStipendWeight(1);
        Weight.setSalaryWeight(1);
        Double jobScore = newJob1.getScore();
        assertEquals(22751.15, jobScore,1.0);
    }


}
