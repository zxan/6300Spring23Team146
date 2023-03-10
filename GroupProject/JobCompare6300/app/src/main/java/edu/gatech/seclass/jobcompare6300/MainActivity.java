package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBaseHelper databaseHelper = new DataBaseHelper(MainActivity.this);
        databaseHelper.insertInitialWeights();
        databaseHelper.setWeights();

        Button currentJob = (Button) findViewById(R.id.EditJobButtonID);
        Button jobOffer = (Button) findViewById(R.id.JobOfferButtonID);
        Button comparisonSettings = (Button) findViewById(R.id.ComparisonSettingsButtonID);
        Button compareJobs = (Button) findViewById(R.id.CompareJobsButtonID);

        currentJob.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CurrentJob.class);
                startActivity(myIntent);
            }
        });

        jobOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), JobOffers.class);
                startActivity(myIntent);
            }
        });

        comparisonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ComparisonSettings.class);
                startActivity(myIntent);
            }
        });

        compareJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CompareJobs.class);
                startActivity(myIntent);
            }
        });
    }
}