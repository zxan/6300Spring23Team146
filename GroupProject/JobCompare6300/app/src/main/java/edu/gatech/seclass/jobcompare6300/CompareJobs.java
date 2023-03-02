package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class CompareJobs extends AppCompatActivity{

    ListView lv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compare_jobs);

        lv_text = findViewById(R.id.lv_text);
        Button compareJobs = findViewById(R.id.CompareJobsJobButtonID);
        Button cancelCompare = findViewById(R.id.CompareJobsCancelJobButtonID);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(CompareJobs.this);
        List<Job> everyone = dataBaseHelper.getEveryone();

        // Use custom adapter that displays only the job title and company
        ArrayAdapter<Job> jobArrayAdapter = new ArrayAdapter<Job>(CompareJobs.this, android.R.layout.simple_list_item_1, android.R.id.text1, everyone) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                text1.setText(getItem(position).getTitle() + " - " + getItem(position).getCompany());

                return view;
            }
        };
        lv_text.setAdapter(jobArrayAdapter);

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
