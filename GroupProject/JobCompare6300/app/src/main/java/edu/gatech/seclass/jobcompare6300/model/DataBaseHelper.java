package edu.gatech.seclass.jobcompare6300.model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String JOB_TABLE = "JOB_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_COMPANY = "COMPANY";
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_COSTINDEX = "COST_INDEX";
    public static final String COLUMN_SALARY = "SALARY";
    public static final String COLUMN_BONUS = "BONUS";
    public static final String COLUMN_RSU = "RSU";
    public static final String COLUMN_RELOCATESTIPEND = "RELOCATE_STIPEND";
    public static final String COLUMN_HOLIDAY = "HOLIDAY";

    public static final String COLUMN_IS_CURRENT = "IS_CURRENT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "jobcompare6300.db", null, 1);
    }

    //first time the database is run
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + JOB_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_COMPANY + " TEXT, " + COLUMN_LOCATION + " TEXT," + COLUMN_COSTINDEX + " INT, " + COLUMN_SALARY + " INT, " + COLUMN_BONUS + " INT, " + COLUMN_RSU + " INT, " + COLUMN_RELOCATESTIPEND + " INT, " + COLUMN_HOLIDAY + " INT, " + COLUMN_IS_CURRENT + " INT)";

        db.execSQL(createTableStatement);
    }

    //whenever the database version changes this is called
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public boolean addOne(Job JobDB, boolean isCurrentJob)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        // Check if there is an existing current job
        if (isCurrentJob) {
            ContentValues cvOld = new ContentValues();
            cvOld.put(COLUMN_IS_CURRENT, false);
            db.update(JOB_TABLE, cvOld, COLUMN_IS_CURRENT + "=?", new String[]{"1"});
        }

        cv.put(COLUMN_ID, JobDB.getId());
        cv.put(COLUMN_TITLE, JobDB.getTitle());
        cv.put(COLUMN_COMPANY,JobDB.getCompany());
        cv.put(COLUMN_LOCATION, JobDB.getLocation());
        cv.put(COLUMN_COSTINDEX, JobDB.getCostIndex());
        cv.put(COLUMN_SALARY, JobDB.getSalary());
        cv.put(COLUMN_BONUS, JobDB.getBonus());
        cv.put(COLUMN_RSU, JobDB.getRsu());
        cv.put(COLUMN_RELOCATESTIPEND, JobDB.getRelocateStipend());
        cv.put(COLUMN_HOLIDAY, JobDB.getHolidays());
        cv.put(COLUMN_IS_CURRENT, isCurrentJob ? 1 : 0);

        long insert = db.insert(JOB_TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public int getNextJobId() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT MAX(" + COLUMN_ID + ") FROM " + JOB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int maxId = 0;
        if (cursor.moveToFirst()) {
            maxId = cursor.getInt(0);
        }
        cursor.close();
        return maxId + 1;
    }

    public boolean updateCurrentJob(Job newCurrentJob) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cvOld = new ContentValues();
        ContentValues cvNew = new ContentValues();

        // Set isCurrentJob to false for the old current job
        cvOld.put(COLUMN_IS_CURRENT, false);
        db.update(JOB_TABLE, cvOld, COLUMN_IS_CURRENT + "=?", new String[]{"1"});

        // Set isCurrentJob to true for the new current job
        cvNew.put(COLUMN_ID, newCurrentJob.getId());
        cvNew.put(COLUMN_TITLE, newCurrentJob.getTitle());
        cvNew.put(COLUMN_COMPANY, newCurrentJob.getCompany());
        cvNew.put(COLUMN_LOCATION, newCurrentJob.getLocation());
        cvNew.put(COLUMN_COSTINDEX, newCurrentJob.getCostIndex());
        cvNew.put(COLUMN_SALARY, newCurrentJob.getSalary());
        cvNew.put(COLUMN_BONUS, newCurrentJob.getBonus());
        cvNew.put(COLUMN_RSU, newCurrentJob.getRsu());
        cvNew.put(COLUMN_RELOCATESTIPEND, newCurrentJob.getRelocateStipend());
        cvNew.put(COLUMN_HOLIDAY, newCurrentJob.getHolidays());
        cvNew.put(COLUMN_IS_CURRENT, true);

        long insert = db.insert(JOB_TABLE, null, cvNew);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Job getJob(int jobId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                JOB_TABLE,
                new String[] {"ID", "TITLE", "COMPANY", "LOCATION", "COST_INDEX", "SALARY", "BONUS", "RSU", "RELOCATE_STIPEND", "HOLIDAY"},
                "id = ?",
                new String[] {String.valueOf(jobId)},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        Job job = new Job(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getInt(5),
                cursor.getInt(6),
                cursor.getInt(7),
                cursor.getInt(8),
                cursor.getInt(9)
        );

        cursor.close();
        db.close();
        return job;
    }


    public List<Job> getEveryone() {
        List<Job> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + JOB_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int entryID = cursor.getInt(0);
                String title = cursor.getString(1);
                String company = cursor.getString(2);
                String location = cursor.getString(3);
                int costindex = cursor.getInt(4);
                int salary = cursor.getInt(5);
                int bonus = cursor.getInt(6);
                int rsu = cursor.getInt(7);
                int relocationstipend = cursor.getInt(8);
                int holiday = cursor.getInt(9);
                int iscurrent = cursor.getInt(10);

                Job jobList = new Job(entryID, title, company, location, costindex, salary, bonus, rsu, relocationstipend, holiday);
                returnList.add(jobList);

            }while (cursor.moveToNext());
        }
        else{

        }

        cursor.close();
        db.close();

        return returnList;
    }
}
