package edu.gatech.seclass.jobcompare6300.model;
import android.annotation.SuppressLint;
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
    public static final String COLUMN_WEIGHTED_VALUE = "COLUMN_WEIGHTED_VALUE";
    public static final String COLUMN_HOLIDAY = "HOLIDAY";
    public static final String WEIGHT_JOB_TABLE = "WEIGHT_JOB_TABLE";
    public static final String WEIGHT_COLUMN_SALARY = "WEIGHTED_SALARY";
    public static final String WEIGHT_COLUMN_BONUS = "WEIGHTED_BONUS";
    public static final String WEIGHT_COLUMN_RSU = "WEIGHTED_RSU";
    public static final String WEIGHT_COLUMN_RELOCATESTIPEND = "WEIGHTED_RELOCATE_STIPEND";
    public static final String WEIGHT_COLUMN_HOLIDAY = "WEIGHTED_HOLIDAY";
    public static final String WEIGHT_COLUMN_ID = "WEIGHTED_ID";
    public static final String COLUMN_IS_CURRENT = "IS_CURRENT";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "jobcompare6300.db", null, 1);
    }

    //first time the database is run
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + JOB_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_COMPANY + " TEXT, " + COLUMN_LOCATION + " TEXT," + COLUMN_COSTINDEX + " INT, " + COLUMN_SALARY + " INT, " + COLUMN_BONUS + " INT, " + COLUMN_RSU + " INT, " + COLUMN_RELOCATESTIPEND + " INT, " + COLUMN_HOLIDAY + " INT, " + COLUMN_IS_CURRENT + " INT, " + COLUMN_WEIGHTED_VALUE + " DECIMAL(10,2))";

        db.execSQL(createTableStatement);

        String createWeightTableStatement = "CREATE TABLE " + WEIGHT_JOB_TABLE + " (" + WEIGHT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WEIGHT_COLUMN_SALARY + " TEXT, " + WEIGHT_COLUMN_BONUS + " TEXT, " + WEIGHT_COLUMN_RSU + " TEXT," + WEIGHT_COLUMN_RELOCATESTIPEND + " INT, " + WEIGHT_COLUMN_HOLIDAY + " INT )";
        db.execSQL(createWeightTableStatement);
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
        cv.put(COLUMN_WEIGHTED_VALUE, JobDB.getScore());

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
        ContentValues cv = new ContentValues();

        // Update columns for the new current job
        cv.put(COLUMN_TITLE, newCurrentJob.getTitle());
        cv.put(COLUMN_COMPANY, newCurrentJob.getCompany());
        cv.put(COLUMN_LOCATION, newCurrentJob.getLocation());
        cv.put(COLUMN_COSTINDEX, newCurrentJob.getCostIndex());
        cv.put(COLUMN_SALARY, newCurrentJob.getSalary());
        cv.put(COLUMN_BONUS, newCurrentJob.getBonus());
        cv.put(COLUMN_RSU, newCurrentJob.getRsu());
        cv.put(COLUMN_RELOCATESTIPEND, newCurrentJob.getRelocateStipend());
        cv.put(COLUMN_HOLIDAY, newCurrentJob.getHolidays());
        cv.put(COLUMN_IS_CURRENT, true);

        int rowsAffected = db.update(JOB_TABLE, cv, COLUMN_ID + "=?", new String[] { String.valueOf(newCurrentJob.getId()) });
        db.close();

        return rowsAffected > 0;
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
        String queryString = "SELECT * FROM " + JOB_TABLE + " ORDER BY " + COLUMN_WEIGHTED_VALUE + " Desc";
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


    public void insertInitialWeights(){
        Cursor c = null;
        String query = "SELECT " + WEIGHT_COLUMN_ID + ","+WEIGHT_COLUMN_SALARY+ ","+WEIGHT_COLUMN_BONUS+ ","+WEIGHT_COLUMN_RSU+ ","+WEIGHT_COLUMN_RELOCATESTIPEND+","+WEIGHT_COLUMN_HOLIDAY+" FROM " + WEIGHT_JOB_TABLE;

        ContentValues cvNew = new ContentValues();
        SQLiteDatabase db = this.getReadableDatabase();
        c = db.rawQuery(query, null);
        c.moveToFirst();
        if( c.getCount() < 1) {

            cvNew.put(WEIGHT_COLUMN_ID, 1);
            cvNew.put(WEIGHT_COLUMN_SALARY, 1);
            cvNew.put(WEIGHT_COLUMN_BONUS, 1);
            cvNew.put(WEIGHT_COLUMN_RSU, 1);
            cvNew.put(WEIGHT_COLUMN_RELOCATESTIPEND, 1);
            cvNew.put(WEIGHT_COLUMN_HOLIDAY, 1);
            long insert = db.insert(WEIGHT_JOB_TABLE, null, cvNew);
        }

        c.close();
        db.close();
    }
    public void updateWeights(String salary,String bonus,String rsu,String relocation,String holiday){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cvOld = new ContentValues();
        cvOld.put(WEIGHT_COLUMN_SALARY, salary);
        cvOld.put(WEIGHT_COLUMN_BONUS, bonus);
        cvOld.put(WEIGHT_COLUMN_RSU, rsu);
        cvOld.put(WEIGHT_COLUMN_RELOCATESTIPEND, relocation);
        cvOld.put(WEIGHT_COLUMN_HOLIDAY,holiday);
        int update = db.update(WEIGHT_JOB_TABLE, cvOld, WEIGHT_COLUMN_ID + "=?", new String[]{"1"});
        db.close();
    }
    public void setWeights() {
        List<Weight> weightList = new ArrayList<>();
        String queryString = "SELECT * FROM " + WEIGHT_JOB_TABLE + " WHERE WEIGHTED_ID = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do {
                int entryID = cursor.getInt(0);
                int salary = cursor.getInt(1);
                int bonus = cursor.getInt(2);
                int rsu = cursor.getInt(3);
                int relocationstipend = cursor.getInt(4);
                int holiday = cursor.getInt(5);


                Weight.setSalaryWeight(salary);
                Weight.setBonusWeight(bonus);
                Weight.setRsuWeight(rsu);
                Weight.setRelocationStipendWeight(relocationstipend);
                Weight.setHolidaysWeight(holiday);

            }while (cursor.moveToNext());
        }
        else{

        }

        cursor.close();
        db.close();

    }

    public void updateJobScore(int id ,double score){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cvOld = new ContentValues();
        cvOld.put(COLUMN_WEIGHTED_VALUE, score);
        System.out.print(cvOld);
        System.out.print(id);
        db.update(JOB_TABLE, cvOld, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});

        db.close();
    }
    public  boolean isCurrentJobStatusById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        int bool = 0;
        Cursor cursor = db.rawQuery("SELECT *  FROM " + JOB_TABLE +
                " WHERE " + COLUMN_ID + " = " + id , null);

        if (cursor.moveToFirst()){
            do {
                int iscurrent = cursor.getInt(10);
                if(iscurrent == 1){
                    bool = 1;
                }

            }while (cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        if (bool ==1){
            return true;
        }
        else{
            return false;
        }
    }

    public int getJobCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(DISTINCT Id) FROM " + JOB_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);;
        }
        cursor.close();
        db.close();
        return count;
    }

    @SuppressLint("Range")
    public int findCurrentJobID() {
        SQLiteDatabase db = this.getReadableDatabase();
        int id = -1;
        String query = "SELECT " + COLUMN_ID + " FROM " + JOB_TABLE +
                " WHERE " + COLUMN_IS_CURRENT + " = 1";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
        }
        cursor.close();
        db.close();
        return id;
    }
    public boolean hasCurrentJob() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + JOB_TABLE + " WHERE " + COLUMN_IS_CURRENT + "=1";
        Cursor cursor = db.rawQuery(query, null);
        boolean hasCurrentJob = cursor.moveToFirst();
        cursor.close();
        return hasCurrentJob;
    }
}