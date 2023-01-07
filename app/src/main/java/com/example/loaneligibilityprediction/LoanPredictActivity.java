package com.example.loaneligibilityprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class LoanPredictActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_predict);
        TextView predict_TV = findViewById(R.id.predict_textView);
        ImageView predict_IV = findViewById(R.id.predit_imageView);
        Intent i = getIntent();
        String data = i.getStringExtra("Predict");
        Log.d("tushar", "Laon Activity   :"+data);
        if(data.equals("\"1\"")){
            predict_IV.setImageResource(R.drawable.loan_pass);
            predict_TV.setText("Your Loan Application Approved  ");
        }
        if(data.equals("\"0\"")){
            predict_IV.setImageResource(R.drawable.laon_fail);
            predict_TV.setText("Sorry Your Loan Application is Filed");
        }

    }
}