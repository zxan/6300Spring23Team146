package edu.gatech.seclass.jobcompare6300.model;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class Job {

    private int id;
    private String title;
    private String company;
    private String location;
    private int costIndex;
    private int salary;
    private int bonus;
    private int rsu;
    private int relocateStipend;
    private int holidays;
    private boolean isCurrentJob;

    private boolean isSelected;

    public Job(int id, String title, String company, String location, int costIndex, int salary, int bonus, int rsu, int relocateStipend, int holidays) {
        this.id = id;
        this.title = title;
        this.company = company;
        this.location = location;
        this.costIndex = costIndex;
        this.salary = salary;
        this.bonus = bonus;
        this.rsu = rsu;
        this.relocateStipend = relocateStipend;
        this.holidays = holidays;
        this.isCurrentJob = false;
        this.isSelected = false;
    }

    public void editJob(String title, String company, String location, int costIndex, int salary, int bonus, int rsu, int relocateStipend, int holidays) {
        this.title = title;
        this.company = company;
        this.location = location;
        this.costIndex = costIndex;
        this.salary = salary;
        this.bonus = bonus;
        this.rsu = rsu;
        this.relocateStipend = relocateStipend;
        this.holidays = holidays;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getCompany() {return company; }
    public String getLocation() {
        return location;
    }
    public int getCostIndex() {
        return costIndex;
    }
    public int getSalary() {
        return salary;
    }
    public int getBonus() {
        return bonus;
    }
    public int getRsu() {
        return rsu;
    }
    public int getRelocateStipend() {
        return relocateStipend;
    }
    public int getHolidays() {
        return holidays;
    }

    public boolean getCurrentJob() {
        return isCurrentJob;
    }


    public void setAsCurrentJob() {
        this.isCurrentJob = true;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setIsCurrentJob(boolean isCurrentJob) {
        this.isCurrentJob = isCurrentJob;
    }

    @SuppressLint("Range")
    public void updateCurrentJobStatus(Context context) {
        DataBaseHelper dbHelper = new DataBaseHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DataBaseHelper.COLUMN_IS_CURRENT + " FROM " + DataBaseHelper.JOB_TABLE +
                " WHERE " + DataBaseHelper.COLUMN_ID + " = " + this.id, null);

        if (cursor.moveToFirst()) {
            this.isCurrentJob = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COLUMN_IS_CURRENT)) == 1;
        }

        cursor.close();
        db.close();
    }

    public double getScore() {

        double sum = Weight.getSalaryWeight() + Weight.getBonusWeight() + Weight.getRsuWeight() + Weight.getRelocationStipendWeight() + Weight.getHolidaysWeight();
        double AYS = salary * 100 / costIndex;
        double AYB = bonus * 100 / costIndex;
        double score = Weight.getSalaryWeight() / sum * AYS
                + Weight.getBonusWeight() / sum * AYB
                + Weight.getRsuWeight() / sum * (rsu / 4)
                + Weight.getRelocationStipendWeight() / sum * relocateStipend
                + Weight.getHolidaysWeight() / sum * (holidays * AYS / 260);
        return score;
    }

    public String toString() {
        String str = String.format("%-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s %-8s", id, title, company, location, salary, bonus, rsu, relocateStipend, holidays);
        if (isCurrentJob) {
            str += "  <- Current Job";
        }
        return str;
    }

    public List<String> getJobParameters() {
        List<String> jobParams = new ArrayList<>();

        jobParams.add("ID: " + id);
        jobParams.add("Title: " + title);
        jobParams.add("Company: " + company);
        jobParams.add("Location: " + location);
        jobParams.add("Salary: " + salary);
        jobParams.add("Bonus: " + bonus);
        jobParams.add("RSU: " + rsu);
        jobParams.add("Relocate Stipend: " + relocateStipend);
        jobParams.add("Holidays: " + holidays);

        return jobParams;
    }


    public String toShort() {
        String str = String.format("%-8s %-8s %-8s", id, title, company);
        if (isCurrentJob) {
            str += "  <- Current Job";
        }
        return str;
    }


}