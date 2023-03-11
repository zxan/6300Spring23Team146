package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
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
        // Use custom adapter that displays only the job title and company for jobs that are currently active
        ArrayAdapter<Job> jobArrayAdapter = new ArrayAdapter<Job>(CompareJobs.this, android.R.layout.simple_list_item_1, android.R.id.text1, everyone) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView text1 = view.findViewById(android.R.id.text1);
                Job job = getItem(position);
                job.updateCurrentJobStatus(getContext());

                if (job.isCurrentJob()) {
                    text1.setText("Title: " +job.getTitle() + "      Company: " + job.getCompany() + " (Current)");
                } else {
                    text1.setText("Title: " +job.getTitle() + "      Company: " + job.getCompany());
                }

                if (job.isSelected()) {
                    text1.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.teal_700));
                } else {
                    text1.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
                }

                // Keep track of the number of selected jobs
                int numSelectedJobs = 0;
                for (int i = 0; i < getCount(); i++) {
                    Job j = getItem(i);
                    if (j.isSelected()) {
                        numSelectedJobs++;
                    }
                }

                // Disable the click event if two jobs are already selected
                if (numSelectedJobs == 2) {
                    if (job.isSelected()) {
                        text1.setEnabled(true);
                        text1.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View view) {
                                job.setSelected(!job.isSelected());
                                notifyDataSetChanged();
                            }
                        });
                    } else {
                        text1.setEnabled(false);
                    }
                } else {
                    text1.setEnabled(true);
                    text1.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            job.setSelected(!job.isSelected());
                            notifyDataSetChanged();
                        }
                    });
                }

                return view;
            }

        };

        lv_text.setAdapter(jobArrayAdapter);


        compareJobs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                List<Integer> selectedIds = new ArrayList<>();
                for (int i = 0; i < everyone.size(); i++) {
                    Job job = everyone.get(i);
                    if (job.isSelected()) {
                        selectedIds.add(job.getId());
                    }
                }
                Intent myIntent = new Intent(view.getContext(), JobComparison.class);
                myIntent.putIntegerArrayListExtra("selectedIds", (ArrayList<Integer>) selectedIds);
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
