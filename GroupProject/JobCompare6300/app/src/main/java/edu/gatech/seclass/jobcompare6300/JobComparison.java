package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;

public class JobComparison extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_comparions);

        Intent intent = getIntent();
        List<Integer> selectedIds = intent.getIntegerArrayListExtra("selectedIds");


        // Get the IDs of the TextViews that will display the job parameters
        TextView titleTextView = findViewById(R.id.compareJob1Title);
        TextView titleTextView2 = findViewById(R.id.compareJob2Title);
        TextView companyTextView = findViewById(R.id.compareJob1Company);
        TextView companyTextView2 = findViewById(R.id.compareJob2Company);
        TextView locationTextView = findViewById(R.id.compareJob1Location);
        TextView locationTextView2 = findViewById(R.id.compareJob2Location);
        TextView COLTextView = findViewById(R.id.compareJob1COL);
        TextView COLTextView2 = findViewById(R.id.compareJob2COL);
        TextView salaryTextView = findViewById(R.id.compareJob1Salary);
        TextView salaryTextView2 = findViewById(R.id.compareJob2Salary);
        TextView bonusTextView = findViewById(R.id.compareJob1Bonus);
        TextView bonusTextView2 = findViewById(R.id.compareJob2Bonus);
        TextView rsuTextView = findViewById(R.id.compareJob1RSU);
        TextView rsuTextView2 = findViewById(R.id.compareJob2RSU);
        TextView relocateStipendTextView = findViewById(R.id.compareJob1Relocation);
        TextView relocateStipendTextView2 = findViewById(R.id.compareJob2Relocation);
        TextView holidaysTextView = findViewById(R.id.compareJob1PTO);
        TextView holidaysTextView2 = findViewById(R.id.compareJob2PTO);

        // Get the ID of the job to display from the intent extras
        int jobId = intent.getIntExtra("jobId", -1);

        DataBaseHelper db = new DataBaseHelper(this);
        Job job1 = db.getJob(selectedIds.get(0));
        Job job2 = db.getJob(selectedIds.get(1));

        titleTextView.setText(job1.getTitle());
        companyTextView.setText(job1.getCompany());
        locationTextView.setText(job1.getLocation());
        COLTextView.setText(String.valueOf(job1.getCostIndex()));
        salaryTextView.setText(String.valueOf(job1.getSalary()));
        bonusTextView.setText(String.valueOf(job1.getBonus()));
        rsuTextView.setText(String.valueOf(job1.getRsu()));
        relocateStipendTextView.setText(String.valueOf(job1.getRelocateStipend()));
        holidaysTextView.setText(String.valueOf(job1.getHolidays()));

        titleTextView2.setText(job2.getTitle());
        companyTextView2.setText(job2.getCompany());
        locationTextView2.setText(job2.getLocation());
        COLTextView2.setText(String.valueOf(job2.getCostIndex()));
        salaryTextView2.setText(String.valueOf(job2.getSalary()));
        bonusTextView2.setText(String.valueOf(job2.getBonus()));
        rsuTextView2.setText(String.valueOf(job2.getRsu()));
        relocateStipendTextView2.setText(String.valueOf(job2.getRelocateStipend()));
        holidaysTextView2.setText(String.valueOf(job2.getHolidays()));


        Button goToCompareAnother = (Button) findViewById(R.id.CompareAnotherJobButtonID);
        Button mainMenuFromComparison = (Button) findViewById(R.id.CompareMainMenuButtonID);

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
