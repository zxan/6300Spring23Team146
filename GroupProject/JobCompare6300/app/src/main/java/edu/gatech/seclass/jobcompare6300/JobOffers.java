package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JobOffers extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_offer);

        Button saveJobOffer = (Button) findViewById(R.id.SaveJobOfferButtonID);
        Button cancelJobOffer = (Button) findViewById(R.id.CancelJobOfferButtonID);
        Button compareJobOffer = (Button) findViewById(R.id.CompareJobOfferButtonID);

        saveJobOffer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), JobOffers.class);
                startActivity(myIntent);
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
                Intent myIntent = new Intent(view.getContext(), JobComparison.class);
                startActivity(myIntent);
            }
        });
    }
}
