package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class CompareJobs extends AppCompatActivity{

    ListView lv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs);

        lv_text = (ListView) findViewById(R.id.lv_text);
        Button compareJobs = (Button) findViewById(R.id.CompareJobsJobButtonID);
        Button cancelCompare = (Button) findViewById(R.id.CompareJobsCancelJobButtonID);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(CompareJobs.this);
        List<Job> everyone = dataBaseHelper.getEveryone();
        Toast.makeText(CompareJobs.this, everyone.toString(), Toast.LENGTH_SHORT).show();

        ArrayAdapter JobArrayAdapter = new ArrayAdapter<Job>(CompareJobs.this, android.R.layout.simple_list_item_1, everyone);
        lv_text.setAdapter(JobArrayAdapter);

        compareJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), JobComparison.class);
                startActivity(myIntent);
            }
        });

        cancelCompare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}