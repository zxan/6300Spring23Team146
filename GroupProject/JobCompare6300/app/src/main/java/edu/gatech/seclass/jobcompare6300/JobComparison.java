package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class JobComparison extends AppCompatActivity{

    ListView lv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparions);

        Button goToCompareAnother = (Button) findViewById(R.id.CompareAnotherJobButtonID);
        Button mainMenuFromComparison = (Button) findViewById(R.id.CompareMainMenuButtonID);

        DataBaseHelper dataBaseHelper = new DataBaseHelper(JobComparison.this);
        List<Job> everyone = dataBaseHelper.getEveryone();
        Toast.makeText(JobComparison.this, everyone.toString(), Toast.LENGTH_SHORT).show();

        ArrayAdapter JobArrayAdapter = new ArrayAdapter<Job>(JobComparison.this, android.R.layout.simple_list_item_1, everyone);
        lv_text.setAdapter(JobArrayAdapter);

        goToCompareAnother.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), CompareJobs.class);
                startActivity(myIntent);
            }
        });

        mainMenuFromComparison.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
            }
        });
    }
}
