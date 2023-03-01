package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.model.Job;

public class CurrentJob  extends AppCompatActivity{

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



                if (invalidinput()){
                    return;
                }
                else{
                    int intJobCol = Integer.parseInt(editCurrentJobCol.getText().toString());
                    int intJobSalary = Integer.parseInt(editCurrentJobSalary.getText().toString());
                    int intJobBonus = Integer.parseInt(editCurrentJobBonus.getText().toString());
                    int intJobRSU = Integer.parseInt(editCurrentJobRsuAward.getText().toString());
                    int intJobRelocation = Integer.parseInt(editCurrentJobRelocation.getText().toString());
                    int intJobHolidays = Integer.parseInt(editCurrentJobPersonalHolidays.getText().toString());

                    Job Current_Job = new Job(1,editCurrentJobTitle.getText().toString(), editCurrentJobCompany.getText().toString(), editCurrentJobLocation.getText().toString(), intJobCol, intJobSalary, intJobBonus, intJobRSU, intJobRelocation, intJobHolidays);
                    Current_Job.setAsCurrentJob();
                    Toast.makeText(CurrentJob.this, "Success = " + Current_Job, Toast.LENGTH_SHORT).show();

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

    public boolean invalidinput(){
        boolean a = false, b= false, c= false, d= false, g= false, f= false, h = false, i = false, j = false;

        if (editCurrentJobTitle.getText().toString().trim().length() == 0)  {
            editCurrentJobCompany.setError("Value cannot be empty");
            h = true;
        }
        if (editCurrentJobTitle.getText().toString().trim().length() == 0)  {
            editCurrentJobTitle.setError("Input cannot be empty");
            i = true;
        }

        if (editCurrentJobLocation.getText().toString().trim().length() == 0)  {
            editCurrentJobLocation.setError("Input cannot be empty");
            j = true;
        }

        try {
            int intJobCol = Integer.parseInt(editCurrentJobCol.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobCol.setError("Input is not integer or is empty");
            a = true;
        }



        try {
            int intJobSalary = Integer.parseInt(editCurrentJobSalary.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobSalary.setError("Input is not integer or is empty");
            b = true;
        }
        try {
            int intJobBonus = Integer.parseInt(editCurrentJobBonus.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobBonus.setError("Input is not integer or is empty");
            c = true;
        }

        try {
            int intJobRSU = Integer.parseInt(editCurrentJobRsuAward.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobRsuAward.setError("Input is not integer or is empty");
            d = true;
        }

        try {
            int intJobRelocation = Integer.parseInt(editCurrentJobRelocation.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobRelocation.setError("Input is not integer or is empty");
            g = true;
        }

        try {
            int intJobHolidays = Integer.parseInt(editCurrentJobPersonalHolidays.getText().toString());
        } catch (NumberFormatException e){
            editCurrentJobPersonalHolidays.setError("Input is not integer or is empty");
            f = true;
        }
        if (a == true || b  == true || c == true || d == true || f  == true || g  == true || h == true){
            return true;
        }
        else{
            return false;
        }
    }
}
