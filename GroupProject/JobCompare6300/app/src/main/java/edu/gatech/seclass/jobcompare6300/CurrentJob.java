package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class CurrentJob extends AppCompatActivity {

    private EditText editCurrentJobTitle;
    private EditText editCurrentJobCompany;
    private EditText editCurrentJobLocation;
    private EditText editCurrentJobCol;
    private EditText editCurrentJobSalary;
    private EditText editCurrentJobBonus;
    private EditText editCurrentJobRsuAward;
    private EditText editCurrentJobRelocation;
    private EditText editCurrentJobPersonalHolidays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_job);

        Button saveAndReturnHome = (Button) findViewById(R.id.SaveJobButtonID);
        Button cancelCurrent = (Button) findViewById(R.id.CancelJobButtonID);

        editCurrentJobTitle = findViewById(R.id.textCurrentJobTitle);
        editCurrentJobCompany = findViewById(R.id.currentJobCompany);
        editCurrentJobLocation = findViewById(R.id.currentJobLocation);
        editCurrentJobCol = findViewById(R.id.currentJobCOL);
        editCurrentJobSalary = findViewById(R.id.currentJobSalary);
        editCurrentJobBonus = findViewById(R.id.currentJobBonus);
        editCurrentJobRsuAward = findViewById(R.id.currentJobRSU);
        editCurrentJobRelocation = findViewById(R.id.currentJobRelocation);
        editCurrentJobPersonalHolidays = findViewById(R.id.currentJobPTO);
        DataBaseHelper databaseHelper = new DataBaseHelper(CurrentJob.this);
        int id = databaseHelper.findCurrentJobID();
        if(id != -1){
            Job job = databaseHelper.getJob(id);
            editCurrentJobTitle.setText(job.getTitle());
            editCurrentJobCompany.setText(job.getCompany());
            editCurrentJobLocation.setText(job.getLocation());
            editCurrentJobCol.setText(String.valueOf(job.getCostIndex()));
            editCurrentJobSalary.setText(String.valueOf(job.getSalary()));
            editCurrentJobBonus.setText(String.valueOf(job.getBonus()));
            editCurrentJobRsuAward.setText(String.valueOf(job.getRsu()));
            editCurrentJobRelocation.setText(String.valueOf(job.getRelocateStipend()));
            editCurrentJobPersonalHolidays.setText(String.valueOf(job.getHolidays()));
        }

        saveAndReturnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (invalidInput()) {
                    return;
                } else {
                    int intJobCol = Integer.parseInt(editCurrentJobCol.getText().toString());
                    int intJobSalary = Integer.parseInt(editCurrentJobSalary.getText().toString());
                    int intJobBonus = Integer.parseInt(editCurrentJobBonus.getText().toString());
                    int intJobRSU = Integer.parseInt(editCurrentJobRsuAward.getText().toString());
                    int intJobRelocation = Integer.parseInt(editCurrentJobRelocation.getText().toString());
                    int intJobHolidays = Integer.parseInt(editCurrentJobPersonalHolidays.getText().toString());




                    if (databaseHelper.hasCurrentJob() == false){
                        int id = databaseHelper.getNextJobId();
                        Job currentJob = new Job(id, editCurrentJobTitle.getText().toString(), editCurrentJobCompany.getText().toString(), editCurrentJobLocation.getText().toString(), intJobCol, intJobSalary, intJobBonus, intJobRSU, intJobRelocation, intJobHolidays);
                        currentJob.setAsCurrentJob();
                        boolean isCurrentJob = currentJob.getCurrentJob();
                        boolean success = databaseHelper.addOne(currentJob, isCurrentJob);
                        Toast.makeText(CurrentJob.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(myIntent);
                    }
                    else{
                        int id = databaseHelper.findCurrentJobID();
                        Job currentJob = new Job(id, editCurrentJobTitle.getText().toString(), editCurrentJobCompany.getText().toString(), editCurrentJobLocation.getText().toString(), intJobCol, intJobSalary, intJobBonus, intJobRSU, intJobRelocation, intJobHolidays);
                        boolean success = databaseHelper.updateCurrentJob(currentJob);
                        Toast.makeText(CurrentJob.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                        startActivity(myIntent);
                    }

                }
            }
        });

        cancelCurrent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    public boolean invalidInput() {
        List<EditText> errorFields = new ArrayList<>();

        if (editCurrentJobTitle.getText().toString().trim().length() == 0)  {
            editCurrentJobTitle.setError("Value cannot be empty");
            errorFields.add(editCurrentJobTitle);
        }

        if (editCurrentJobCompany.getText().toString().trim().length() == 0)  {
            editCurrentJobCompany.setError("Input cannot be empty");
            errorFields.add(editCurrentJobCompany);
        }

        if (editCurrentJobLocation.getText().toString().trim().length() == 0)  {
            editCurrentJobLocation.setError("Input cannot be empty");
            errorFields.add(editCurrentJobLocation);
        }else {

            if (!editCurrentJobLocation.getText().toString().matches(".*[a-zA-Z].*"))  {
                editCurrentJobLocation.setError("No integers.Please Enter City and State");
                errorFields.add(editCurrentJobLocation);
            }
        }

        if (editCurrentJobCol.getText().toString().trim().length() == 0)  {
            editCurrentJobCol.setError("Value cannot be empty");
            errorFields.add(editCurrentJobCol);
        }else {

            if (Integer.valueOf(editCurrentJobCol.getText().toString()) <1)  {
                editCurrentJobCol.setError("Number must be between 1-350");
                errorFields.add(editCurrentJobCol);
            }

            if (Integer.valueOf(editCurrentJobCol.getText().toString()) > 350) {
                editCurrentJobCol.setError("Number must be between 1-350");
                errorFields.add(editCurrentJobCol);
            }
        }

        if (editCurrentJobSalary.getText().toString().trim().length() == 0)  {
            editCurrentJobSalary.setError("Input cannot be empty");
            errorFields.add(editCurrentJobSalary);
        }else {

            if (Integer.valueOf(editCurrentJobSalary.getText().toString()) <1)  {
                editCurrentJobSalary.setError("Number must be between 1-25000000");
                errorFields.add(editCurrentJobSalary);
            }

            if (Integer.valueOf(editCurrentJobSalary.getText().toString()) > 25000000) {
                editCurrentJobSalary.setError("Number must be between 1-25000000");
                errorFields.add(editCurrentJobSalary);
            }
        }

        if (editCurrentJobBonus.getText().toString().trim().length() == 0)  {
            editCurrentJobBonus.setError("Input cannot be empty");
            errorFields.add(editCurrentJobBonus);
        }else {

            if (Integer.valueOf(editCurrentJobBonus.getText().toString()) <0)  {
                editCurrentJobBonus.setError("Number must be between 0-25000000");
                errorFields.add(editCurrentJobBonus);
            }

            if (Integer.valueOf(editCurrentJobBonus.getText().toString()) > 25000000) {
                editCurrentJobBonus.setError("Number must be between 0-25000000");
                errorFields.add(editCurrentJobBonus);
            }
        }

        if (editCurrentJobRsuAward.getText().toString().trim().length() == 0)  {
            editCurrentJobRsuAward.setError("Value cannot be empty");
            errorFields.add(editCurrentJobRsuAward);
        }else {

            if (Integer.valueOf(editCurrentJobRsuAward.getText().toString()) <0)  {
                editCurrentJobRsuAward.setError("Number must be between 0-25000000");
                errorFields.add(editCurrentJobRsuAward);
            }

            if (Integer.valueOf(editCurrentJobRsuAward.getText().toString()) > 25000000) {
                editCurrentJobRsuAward.setError("Number must be between 0-25000000");
                errorFields.add(editCurrentJobRsuAward);
            }
        }

        if (editCurrentJobRelocation.getText().toString().trim().length() == 0)  {
            editCurrentJobRelocation.setError("Input cannot be empty");
            errorFields.add(editCurrentJobRelocation);
        }else {

            if (Integer.valueOf(editCurrentJobRelocation.getText().toString()) < 0)  {
                editCurrentJobRelocation.setError("Number must be between 0-25000");
                errorFields.add(editCurrentJobRelocation);
            }

            if (Integer.valueOf(editCurrentJobRelocation.getText().toString()) > 25000) {
                editCurrentJobRelocation.setError("Number must be between 0-25000");
                errorFields.add(editCurrentJobRelocation);
            }
        }

        if (editCurrentJobPersonalHolidays.getText().toString().trim().length() == 0)  {
            editCurrentJobPersonalHolidays.setError("Input cannot be empty");
            errorFields.add(editCurrentJobPersonalHolidays);
        }else {

            if (Integer.valueOf(editCurrentJobPersonalHolidays.getText().toString()) <0)  {
                editCurrentJobPersonalHolidays.setError("Number must be between 0-20");
                errorFields.add(editCurrentJobPersonalHolidays);
            }

            if (Integer.valueOf(editCurrentJobPersonalHolidays.getText().toString()) > 20) {
                editCurrentJobPersonalHolidays.setError("Number must be between 0-20");
                errorFields.add(editCurrentJobPersonalHolidays);
            }
        }


        if (errorFields.size() > 0) {
            // Focus the first EditText view with an error
            errorFields.get(0).requestFocus();
            return true;
        } else {
            return false;
        }
    }
}
