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
                int count = dataBaseHelper.getJobCount();
                Boolean hasCurrent = dataBaseHelper.hasCurrentJob();
                if(!hasCurrent){
                    compareJobOffer.setEnabled(false);
                    Toast.makeText(JobOffers.this, "Please enter current job to compare. ",Toast.LENGTH_LONG).show();
                } else if(count < 2 ){
                    compareJobOffer.setEnabled(false);
                    Toast.makeText(JobOffers.this, "Two jobs must exist to compare ",Toast.LENGTH_LONG).show();
                }else {
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
        }else {

            if (!editJobLocation.getText().toString().matches(".*[a-zA-Z].*"))  {
                editJobLocation.setError("No integers.Please Enter City and State");
                errorFields.add(editJobLocation);
            }
        }

        if (editJobCol.getText().toString().trim().length() == 0)  {
            editJobCol.setError("Value cannot be empty");
            errorFields.add(editJobCol);
        }else {

            if (Integer.valueOf(editJobCol.getText().toString()) <1)  {
                editJobCol.setError("Number must be between 1-350");
                errorFields.add(editJobCol);
            }

            if (Integer.valueOf(editJobCol.getText().toString()) > 350) {
                editJobCol.setError("Number must be between 1-350");
                errorFields.add(editJobCol);
            }
        }

        if (editJobSalary.getText().toString().trim().length() == 0)  {
            editJobSalary.setError("Input cannot be empty");
            errorFields.add(editJobSalary);
        }else {

            if (Integer.valueOf(editJobSalary.getText().toString()) <1)  {
                editJobSalary.setError("Number must be between 1-25000000");
                errorFields.add(editJobSalary);
            }

            if (Integer.valueOf(editJobSalary.getText().toString()) > 25000000) {
                editJobSalary.setError("Number must be between 1-25000000");
                errorFields.add(editJobSalary);
            }
        }

        if (editJobBonus.getText().toString().trim().length() == 0)  {
            editJobBonus.setError("Input cannot be empty");
            errorFields.add(editJobBonus);
        }else {

            if (Integer.valueOf(editJobBonus.getText().toString()) <0)  {
                editJobBonus.setError("Number must be between 0-25000000");
                errorFields.add(editJobBonus);
            }

            if (Integer.valueOf(editJobBonus.getText().toString()) > 25000000) {
                editJobBonus.setError("Number must be between 0-25000000");
                errorFields.add(editJobBonus);
            }
        }

        if (editJobRsuAward.getText().toString().trim().length() == 0)  {
            editJobRsuAward.setError("Value cannot be empty");
            errorFields.add(editJobRsuAward);
        }else {

            if (Integer.valueOf(editJobRsuAward.getText().toString()) <0)  {
                editJobRsuAward.setError("Number must be between 0-25000000");
                errorFields.add(editJobRsuAward);
            }

            if (Integer.valueOf(editJobRsuAward.getText().toString()) > 25000000) {
                editJobRsuAward.setError("Number must be between 0-25000000");
                errorFields.add(editJobRsuAward);
            }
        }

        if (editJobRelocation.getText().toString().trim().length() == 0)  {
            editJobRelocation.setError("Input cannot be empty");
            errorFields.add(editJobRelocation);
        }else {

            if (Integer.valueOf(editJobRelocation.getText().toString()) <1)  {
                editJobRelocation.setError("Number must be between 0-25000000");
                errorFields.add(editJobRelocation);
            }

            if (Integer.valueOf(editJobRelocation.getText().toString()) > 25000000) {
                editJobRelocation.setError("Number must be between 0-25000000");
                errorFields.add(editJobRelocation);
            }
        }

        if (editJobPersonalHolidays.getText().toString().trim().length() == 0)  {
            editJobPersonalHolidays.setError("Input cannot be empty");
            errorFields.add(editJobPersonalHolidays);
        }else {

            if (Integer.valueOf(editJobPersonalHolidays.getText().toString()) <0)  {
                editJobPersonalHolidays.setError("Number must be between 0-20");
                errorFields.add(editJobPersonalHolidays);
            }

            if (Integer.valueOf(editJobPersonalHolidays.getText().toString()) > 20) {
                editJobPersonalHolidays.setError("Number must be between 0-20");
                errorFields.add(editJobPersonalHolidays);
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
