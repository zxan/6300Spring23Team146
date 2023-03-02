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

                    DataBaseHelper databaseHelper = new DataBaseHelper(CurrentJob.this);
                    int id = databaseHelper.getNextJobId();
                    Job currentJob = new Job(id, editCurrentJobTitle.getText().toString(), editCurrentJobCompany.getText().toString(), editCurrentJobLocation.getText().toString(), intJobCol, intJobSalary, intJobBonus, intJobRSU, intJobRelocation, intJobHolidays);
                    currentJob.setAsCurrentJob();
                    boolean success = databaseHelper.addOne(currentJob);
                    Toast.makeText(CurrentJob.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                    startActivity(myIntent);
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
            editCurrentJobCompany.setError("Value cannot be empty");
            errorFields.add(editCurrentJobTitle);
        }

        if (editCurrentJobTitle.getText().toString().trim().length() == 0)  {
            editCurrentJobTitle.setError("Input cannot be empty");
            errorFields.add(editCurrentJobTitle);
        }

        if (editCurrentJobLocation.getText().toString().trim().length() == 0)  {
            editCurrentJobLocation.setError("Input cannot be empty");
            errorFields.add(editCurrentJobLocation);
        }

        try {
            int intJobCol = Integer.parseInt(editCurrentJobCol.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobCol.setError("Input is not integer or is empty");
            errorFields.add(editCurrentJobCol);
        }

        try {
            int intJobSalary = Integer.parseInt(editCurrentJobSalary.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobSalary.setError("Input is not integer or is empty");
            errorFields.add(editCurrentJobSalary);
        }

        try {
            int intJobBonus = Integer.parseInt(editCurrentJobBonus.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobBonus.setError("Input is not integer or is empty");
            errorFields.add(editCurrentJobBonus);
        }

        try {
            int intJobRSU = Integer.parseInt(editCurrentJobRsuAward.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobRsuAward.setError("Input is not integer or is empty");
            errorFields.add(editCurrentJobRsuAward);
        }

        try {
            int intJobRelocation = Integer.parseInt(editCurrentJobRelocation.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobRelocation.setError("Input is not integer or is empty");
            errorFields.add(editCurrentJobRelocation);
        }

        try {
            int intJobHolidays = Integer.parseInt(editCurrentJobPersonalHolidays.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobPersonalHolidays.setError("Input is not integer or is empty");
            errorFields.add(editCurrentJobPersonalHolidays);
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
