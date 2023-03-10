package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class JobOffers extends AppCompatActivity{

    private EditText editJobTitle;
    private EditText editJobCompany;
    private EditText editJobLocation;
    private EditText editJobCol;
    private EditText editJobSalary;
    private EditText editJobBonus;
    private EditText editJobRsuAward;
    private EditText editJobRelocation;
    private EditText editJobPersonalHolidays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offer);
        DataBaseHelper dataBaseHelper = new DataBaseHelper(JobOffers.this);
        List<Job> everyone = dataBaseHelper.getEveryone();
        Button saveJobOffer = (Button) findViewById(R.id.SaveJobOfferButtonID);
        Button cancelJobOffer = (Button) findViewById(R.id.CancelJobOfferButtonID);
        Button compareJobOffer = (Button) findViewById(R.id.CompareJobOfferButtonID);

        editJobTitle = findViewById(R.id.textJobOfferTitle);
        editJobCompany = findViewById(R.id.currentJobOfferCompany);
        editJobLocation = findViewById(R.id.currentJobOfferLocation);
        editJobCol = findViewById(R.id.currentJobOfferCOL);
        editJobSalary = findViewById(R.id.currentJobOfferSalary);
        editJobBonus = findViewById(R.id.currentJobOfferBonus);
        editJobRsuAward = findViewById(R.id.currentJobOfferRSU);
        editJobRelocation = findViewById(R.id.currentJobOfferRelocation);
        editJobPersonalHolidays = findViewById(R.id.currentJobOfferPTO);


        saveJobOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (invalidInput()) {
                    return;
                } else {
                    int intJobCol = Integer.parseInt(editJobCol.getText().toString());
                    int intJobSalary = Integer.parseInt(editJobSalary.getText().toString());
                    int intJobBonus = Integer.parseInt(editJobBonus.getText().toString());
                    int intJobRSU = Integer.parseInt(editJobRsuAward.getText().toString());
                    int intJobRelocation = Integer.parseInt(editJobRelocation.getText().toString());
                    int intJobHolidays = Integer.parseInt(editJobPersonalHolidays.getText().toString());

                    DataBaseHelper databaseHelper = new DataBaseHelper(JobOffers.this);
                    int id = databaseHelper.getNextJobId();
                    Job job = new Job(id, editJobTitle.getText().toString(), editJobCompany.getText().toString(), editJobLocation.getText().toString(), intJobCol, intJobSalary, intJobBonus, intJobRSU, intJobRelocation, intJobHolidays);
                    boolean isCurrentJob = false;  // Set isCurrentJob to false
                    boolean success = databaseHelper.addOne(job, isCurrentJob);
                    Toast.makeText(JobOffers.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), JobOffers.class);
                    startActivity(myIntent);
                    finish();  // Finish the activity
                }


            }
        });

        cancelJobOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });

        compareJobOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                List<Integer> selectedIds = new ArrayList<>();
                int id = 0;

                for (int i = 0; i < everyone.size(); i++) {
                    Job job = everyone.get(i);
                    int jobId = job.getId();
                    boolean isCurrent = dataBaseHelper.isCurrentJobStatusById(jobId);
                    if (isCurrent) {
                        selectedIds.add(job.getId());
                    } else if (jobId > id) {
                        id = jobId;
                    }
                }
                selectedIds.add(id);
                Intent myIntent = new Intent(view.getContext(), JobComparison.class);
                myIntent.putIntegerArrayListExtra("selectedIds", (ArrayList<Integer>) selectedIds);
                startActivity(myIntent);
            }
        });
    }
    public boolean invalidInput() {
        List<EditText> errorFields = new ArrayList<>();

        if (editJobTitle.getText().toString().trim().length() == 0)  {
            editJobTitle.setError("Value cannot be empty");
            errorFields.add(editJobTitle);
        }

        if (editJobCompany.getText().toString().trim().length() == 0)  {
            editJobCompany.setError("Input cannot be empty");
            errorFields.add(editJobCompany);
        }

        if (editJobLocation.getText().toString().trim().length() == 0)  {
            editJobLocation.setError("Input cannot be empty");
            errorFields.add(editJobLocation);
        }

        try {
            int intJobCol = Integer.parseInt(editJobCol.getText().toString());
        } catch (NumberFormatException e){
            editJobCol.setError("Input is not integer or is empty");
            errorFields.add(editJobCol);
        }

        try {
            int intJobSalary = Integer.parseInt(editJobSalary.getText().toString());
        } catch (NumberFormatException e){
            editJobSalary.setError("Input is not integer or is empty");
            errorFields.add(editJobSalary);
        }

        try {
            int intJobBonus = Integer.parseInt(editJobBonus.getText().toString());
        } catch (NumberFormatException e){
            editJobBonus.setError("Input is not integer or is empty");
            errorFields.add(editJobBonus);
        }

        try {
            int intJobRSU = Integer.parseInt(editJobRsuAward.getText().toString());
        } catch (NumberFormatException e){
            editJobRsuAward.setError("Input is not integer or is empty");
            errorFields.add(editJobRsuAward);
        }

        try {
            int intJobRelocation = Integer.parseInt(editJobRelocation.getText().toString());
        } catch (NumberFormatException e){
            editJobRelocation.setError("Input is not integer or is empty");
            errorFields.add(editJobRelocation);
        }

        try {
            int intJobHolidays = Integer.parseInt(editJobPersonalHolidays.getText().toString());
        } catch (NumberFormatException e){
            editJobPersonalHolidays.setError("Input is not integer or is empty");
            errorFields.add(editJobPersonalHolidays);
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
