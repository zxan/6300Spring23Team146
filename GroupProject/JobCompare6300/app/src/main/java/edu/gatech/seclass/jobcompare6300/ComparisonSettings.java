package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.seclass.jobcompare6300.model.DataBaseHelper;
import edu.gatech.seclass.jobcompare6300.model.Job;
import edu.gatech.seclass.jobcompare6300.model.Weight;

public class ComparisonSettings extends AppCompatActivity{
    private EditText updateSalaryWeight;
    private EditText updateBonusWeight;
    private EditText updateRSUWeight;
    private EditText updateRelocationWeight;
    private EditText updatePTOWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_settings);

        Button returnHome = (Button) findViewById(R.id.SaveComparisonButtonID);
        updateSalaryWeight = findViewById(R.id.textComparisonSalaryWeight);
        updateBonusWeight = findViewById(R.id.textComparisonBonusWeight);
        updateRSUWeight = findViewById(R.id.textComparisonRSUWeight);
        updateRelocationWeight = findViewById(R.id.textComparisonRelocationWeight);
        updatePTOWeight = findViewById(R.id.textComparisonPTOWeight);



        returnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String salaryWeight = updateSalaryWeight.getText().toString();
                String bonusWeight = updateBonusWeight.getText().toString();
                String rsuWeight = updateRSUWeight.getText().toString();
                String relocationWeight = updateRelocationWeight.getText().toString();
                String ptoWeight = updatePTOWeight.getText().toString();
                DataBaseHelper databaseHelper = new DataBaseHelper(ComparisonSettings.this);
                databaseHelper.updateWeights(salaryWeight,bonusWeight,rsuWeight,relocationWeight,ptoWeight);
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }
}