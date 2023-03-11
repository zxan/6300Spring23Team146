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

        Button returnSave = (Button) findViewById(R.id.SaveComparisonButtonID);
        Button returnHome = (Button) findViewById(R.id.CancelComparisonButtonID);
        updateSalaryWeight = findViewById(R.id.textComparisonSalaryWeight);
        updateBonusWeight = findViewById(R.id.textComparisonBonusWeight);
        updateRSUWeight = findViewById(R.id.textComparisonRSUWeight);
        updateRelocationWeight = findViewById(R.id.textComparisonRelocationWeight);
        updatePTOWeight = findViewById(R.id.textComparisonPTOWeight);



        returnSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (invalidInput()) {
                    return;
                } else {
                    String salaryWeight = updateSalaryWeight.getText().toString();
                    String bonusWeight = updateBonusWeight.getText().toString();
                    String rsuWeight = updateRSUWeight.getText().toString();
                    String relocationWeight = updateRelocationWeight.getText().toString();
                    String ptoWeight = updatePTOWeight.getText().toString();
                    DataBaseHelper databaseHelper = new DataBaseHelper(ComparisonSettings.this);
                    databaseHelper.updateWeights(salaryWeight, bonusWeight, rsuWeight, relocationWeight, ptoWeight);

                    databaseHelper.setWeights();
                    List<Job> everyone = databaseHelper.getEveryone();
                    for (int i = 0; i < everyone.size(); i++) {
                        Job job = everyone.get(i);
                        int id = job.getId();
                        double score = job.getScore();
                        databaseHelper.updateJobScore(id, score);
                    }
                    Toast.makeText(ComparisonSettings.this, "Weights saved ", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), ComparisonSettings.class);
                    startActivity(myIntent);
                    finish();
                }
            }
        });
        returnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                startActivity(myIntent);
                finish();
            }
        });

    }

    public boolean invalidInput() {
        List<EditText> errorFields = new ArrayList<>();

        if (updateSalaryWeight.getText().toString().trim().isEmpty())  {
            updateSalaryWeight.setError("Value cannot be empty");
            errorFields.add(updateSalaryWeight);

        }else{
            if (Integer.valueOf(updateSalaryWeight.getText().toString()) <1)  {
                updateSalaryWeight.setError("Number must be between 1-5");
                errorFields.add(updateSalaryWeight);
            }

            if (Integer.valueOf(updateSalaryWeight.getText().toString()) > 5) {
                updateSalaryWeight.setError("Number must be between 1-5");
                errorFields.add(updateSalaryWeight);
            }
        }

        if (updateBonusWeight.getText().toString().trim().isEmpty())  {
            updateBonusWeight.setError("Value cannot be empty");
            errorFields.add(updateBonusWeight);

        }else {
            if (Integer.valueOf(updateBonusWeight.getText().toString()) <1)  {
                updateBonusWeight.setError("Number must be between 1-5");
                errorFields.add(updateBonusWeight);
            }

            if (Integer.valueOf(updateBonusWeight.getText().toString()) > 5) {
                updateBonusWeight.setError("Number must be between 1-5");
                errorFields.add(updateBonusWeight);
            }
        }


        if (updateRSUWeight.getText().toString().trim().isEmpty())  {
            updateRSUWeight.setError("Value cannot be empty");
            errorFields.add(updateRSUWeight);

        }else {
            if (Integer.valueOf(updateRSUWeight.getText().toString()) <1)  {
                updateRSUWeight.setError("Number must be between 1-5");
                errorFields.add(updateRSUWeight);
            }

            if (Integer.valueOf(updateRSUWeight.getText().toString()) > 5) {
                updateRSUWeight.setError("Number must be between 1-5");
                errorFields.add(updateRSUWeight);
            }
        }

        if (updateRelocationWeight.getText().toString().trim().isEmpty())  {
            updateRelocationWeight.setError("Value cannot be empty");
            errorFields.add(updateRelocationWeight);

        }else {
            if (Integer.valueOf(updateRelocationWeight.getText().toString()) <1)  {
                updateRelocationWeight.setError("Number must be between 1-5");
                errorFields.add(updateRelocationWeight);
            }

            if (Integer.valueOf(updateRelocationWeight.getText().toString()) > 5) {
                updateRelocationWeight.setError("Number must be between 1-5");
                errorFields.add(updateRelocationWeight);
            }
        }

        if (updatePTOWeight.getText().toString().trim().isEmpty())  {
            updatePTOWeight.setError("Value cannot be empty");
            errorFields.add(updatePTOWeight);

        }else {

            if (Integer.valueOf(updatePTOWeight.getText().toString()) <1)  {
                updatePTOWeight.setError("Number must be between 1-5");
                errorFields.add(updatePTOWeight);
            }

            if (Integer.valueOf(updatePTOWeight.getText().toString()) > 5) {
                updatePTOWeight.setError("Number must be between 1-5");
                errorFields.add(updatePTOWeight);
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