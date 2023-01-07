package com.example.loaneligibilityprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class ActivityDucuments extends AppCompatActivity {

    CheckBox  PAN,passport,voter_ID,electricity_bill;
    Button submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ducuments);
        addListenerOnButtonClick();
    }
    public void addListenerOnButtonClick(){
        //Getting instance of CheckBoxes and Button from the activty_main.xml file  
         PAN= (CheckBox) findViewById(R.id.pancardCheck);
        passport=(CheckBox)findViewById(R.id.pancardCheck);
        voter_ID=(CheckBox)findViewById(R.id.voterCheck);
        electricity_bill=(CheckBox)findViewById(R.id.electricityCheck);
        submitbutton=(Button)findViewById(R.id.button_submit);


        submitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click
                if (PAN.isChecked() && passport.isChecked() && voter_ID.isChecked() && electricity_bill.isChecked()){

                    Toast.makeText(getApplicationContext(),"next page ",Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(ActivityDucuments.this,ActivityDocUplode.class);
                    startActivity(intent1);
                }
                else{
                    Toast.makeText(getApplicationContext(),"failed ",Toast.LENGTH_SHORT).show();

                }

            }
        });


    }}