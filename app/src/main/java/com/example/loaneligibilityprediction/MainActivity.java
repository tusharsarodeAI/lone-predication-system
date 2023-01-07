package com.example.loaneligibilityprediction;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.loaneligibilityprediction.utils.APIClient;
import com.example.loaneligibilityprediction.utils.LoanPredictionMethod;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextInputLayout genderLayout = null;
    AutoCompleteTextView genderText = null;
    int genderSelection = -1;


    TextInputLayout marriedLayout = null;
    AutoCompleteTextView marriedText = null;
    int marriedSelection = -1;


    TextInputLayout dependentsLayout = null;
    AutoCompleteTextView dependentsText = null;
    int dependentsSelection = -1;

    TextInputLayout educationLayout = null;
    AutoCompleteTextView educationText = null;
    int educationSelection = -1;

    TextInputLayout Self_EmployedLayout = null;
    AutoCompleteTextView Self_EmployedText = null;
    int Self_EmployedSelection = -1;


    TextInputLayout ApplicantIncomeLayout = null;
    AutoCompleteTextView ApplicantIncomeText = null;
    int ApplicantIncomeSelection = -1;

    TextInputLayout LoanAmountLayout = null;
    AutoCompleteTextView LoanAmountText = null;
    int LoanAmountSelection = -1;

    TextInputLayout Credit_HistoryLayout = null;
    AutoCompleteTextView Credit_HistoryText = null;
    int Credit_HistorySelection = -1;


    TextInputLayout Property_AreaLayout = null;
    AutoCompleteTextView Property_AreaText = null;
    int Property_AreaSelection = -1;

    Button submit_bt = null;

    LoanPredictionMethod loanPredictionMethod;

    DatabaseReference loanInforef;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loanInforef = FirebaseDatabase.getInstance().getReference().child("loaninfo"); ///

        submit_bt = findViewById(R.id.submit_bt);

        //list gender
        ArrayList<String> gender = new ArrayList<>();
        gender.add("Male");
        gender.add("Female");

        // gender id declaration
        genderLayout = findViewById(R.id.genderTIL);
        genderText = findViewById(R.id.genderTV);

        ArrayAdapter<String> adapter_gender = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, gender);
        genderText.setAdapter(adapter_gender);
        genderText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                              @Override
                                              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                                  String genderStatus = gender.get(i).toString();
                                                  if (genderStatus.equals("Male")) {
                                                      genderSelection = 1;
                                                  }
                                                  if (genderStatus.equals("Female")) {
                                                      genderSelection = 0;
                                                  }
                                                  Log.d("Data", "gender Selection: " + genderSelection);
                                              }

                                          }

        );

        // married list
        ArrayList<String> married = new ArrayList<>();
        married.add("Yes");
        married.add("No");

        // married id declaration
        marriedLayout = findViewById(R.id.marriedTIL);
        marriedText = findViewById(R.id.marriedTV);

        ArrayAdapter<String> adapter_married = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, married);
        marriedText.setAdapter(adapter_married);
        marriedText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view1, int i, long l) {
                String marriedStatus = married.get(i).toString();
                if (marriedStatus.equals("Yes")) {
                    marriedSelection = 1;
                }
                if (marriedStatus.equals("No")) {
                    marriedSelection = 0;
                }
                Log.d("Data", "married Selection: " + marriedSelection);
            }

        });

        // dependents list
        ArrayList<String> dependents = new ArrayList<>();
        dependents.add("0");
        dependents.add("1");
        dependents.add("2");
        dependents.add("3+");

        // married dependents declaration
        dependentsLayout = findViewById(R.id.dependentsTIL);
        dependentsText = findViewById(R.id.dependentsTV);

        // married adapter
        ArrayAdapter<String> adapter_dependents = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, dependents);
        dependentsText.setAdapter(adapter_dependents);

        dependentsText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String dependentsdata = dependents.get(i).toString();
                if (dependentsdata.equals("0")) {
                    dependentsSelection = 0;
                }
                if (dependentsdata.equals("1")) {
                    dependentsSelection = 1;
                }
                if (dependentsdata.equals("2")) {
                    dependentsSelection = 2;
                }
                if (dependentsdata.equals("3+")) {
                    dependentsSelection = 4;
                }
                Log.d("Data", "married Selection: " + dependentsSelection);
            }

        });

        // education list
        ArrayList<String> education = new ArrayList<>();
        education.add("Yes");
        education.add("No");

        // education id declaration
        educationLayout = findViewById(R.id.educationTIL);
        educationText = findViewById(R.id.educationTV);

        // education adapter
        ArrayAdapter<String> adapter_education = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1, education);
        educationText.setAdapter(adapter_education);

        educationText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String eductionStatus = education.get(i).toString();
                if (eductionStatus.equals("Yes")) {
                    educationSelection = 1;
                }
                if (eductionStatus.equals("No")) {
                    educationSelection = 0;
                }
                Log.d("Data", "education Selection: " + educationSelection);
            }

        });

        {
            ArrayList<String> Self_Employed = new ArrayList<>();
            Self_Employed.add("Yes");
            Self_Employed.add("No");

            Self_EmployedLayout = findViewById(R.id.Self_EmployedTIL);
            Self_EmployedText = findViewById(R.id.Self_EmployedTV);

            ArrayAdapter<String> adapter_Self_Employed = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, Self_Employed);
            Self_EmployedText.setAdapter(adapter_Self_Employed);
            Self_EmployedText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String selfempolyed = Self_Employed.get(i).toString();
                    if (selfempolyed.equals("Yes")) {
                        Self_EmployedSelection = 1;
                    }
                    if (selfempolyed.equals("No")) {
                        Self_EmployedSelection = 0;
                    }
                    Log.d("Data", "Self_employed Selection: " + Self_EmployedSelection);
                }

            });
        }


        {
            ArrayList<String> applicantIncome = new ArrayList<>();
            applicantIncome.add("1000");
            applicantIncome.add("2000");
            applicantIncome.add("3000");
            applicantIncome.add("6000");
            applicantIncome.add("10000");
            applicantIncome.add("20000");
            applicantIncome.add("30000");

            ApplicantIncomeLayout = findViewById(R.id.applicantIncomeTIL);
            ApplicantIncomeText = findViewById(R.id.applicantIncomeTV);

            ArrayAdapter<String> adapter_ApplicantIncomeLayout = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, applicantIncome);
            ApplicantIncomeText.setAdapter(adapter_ApplicantIncomeLayout);
            ApplicantIncomeText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String income = applicantIncome.get(i).toString();
                    ApplicantIncomeSelection = Integer.parseInt(income);
                    Log.d("Data", "Self_employed Selection: " + income);
                }

            });
        }
        {
            ArrayList<String> LoanAmount = new ArrayList<>();
            LoanAmount.add("100");
            LoanAmount.add("200");
            LoanAmount.add("300");
            LoanAmount.add("400");
            LoanAmount.add("500");
            LoanAmount.add("600");
            LoanAmount.add("700");
            LoanAmount.add("800");
            LoanAmount.add("900");
            LoanAmount.add("1000");

            LoanAmountLayout = findViewById(R.id.LoanAmountTIL);
            LoanAmountText = findViewById(R.id.LoanAmountTV);

            ArrayAdapter<String> adapter_LoanAmount = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, LoanAmount);
            LoanAmountText.setAdapter(adapter_LoanAmount);
            LoanAmountText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String loanAmount = LoanAmount.get(i).toString();
                    LoanAmountSelection = Integer.parseInt(loanAmount);
                    Log.d("Data", "Self_employed Selection: " + loanAmount);
                }

            });
        }


        {
            ArrayList<String> Credit_History = new ArrayList<>();
            Credit_History.add("Yes");
            Credit_History.add("No");

            Credit_HistoryLayout = findViewById(R.id.Credit_HistoryTIL);
            Credit_HistoryText = findViewById(R.id.Credit_HistoryTV);

            ArrayAdapter<String> adapter_Self_Employed = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, Credit_History);
            Credit_HistoryText.setAdapter(adapter_Self_Employed);
            Credit_HistoryText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = Credit_History.get(i).toString();
                    if (name.equals("Yes")) {
                        Credit_HistorySelection = 1;
                    }
                    if (name.equals("No")) {
                        Credit_HistorySelection = 0;
                    }
                    Log.d("Data", "Self_employed Selection: " + Credit_HistorySelection);
                }

            });
        }

        {   //Semiurban
            //Urban
            //Rural
            ArrayList<String> Property_Area = new ArrayList<>();
            Property_Area.add("Semiurban");
            Property_Area.add("Urban");
            Property_Area.add("Rural");

            Property_AreaLayout = findViewById(R.id.Property_AreaTIL);
            Property_AreaText = findViewById(R.id.Property_AreaTV);

            ArrayAdapter<String> adapter_Self_Employed = new ArrayAdapter<String>(MainActivity.this,
                    android.R.layout.simple_list_item_1, Property_Area);
            Property_AreaText.setAdapter(adapter_Self_Employed);
            Property_AreaText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String property = Property_Area.get(i).toString();
                    if (property.equals("Semiurban")) {
                        Property_AreaSelection = 0;
                    }
                    if (property.equals("Urban")) {
                        Property_AreaSelection = 1;
                    }
                    if (property.equals("Rural")) {
                        Property_AreaSelection = 2;
                    }
                    Log.d("Data", "property Selection: " + Property_AreaSelection + "  " + property);
                }

            });
        }

        submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // api calling phase
                Prediction prediction = new Prediction(
                        genderSelection,
                        marriedSelection,
                        dependentsSelection,
                        educationSelection,
                        Self_EmployedSelection,
                        ApplicantIncomeSelection,
                        LoanAmountSelection,
                        Credit_HistorySelection,
                        Property_AreaSelection
                );

                Log.d("tushar", "Data: " +
                        "Gender Selection " + genderSelection +
                        " Married Selection " + marriedSelection
                        + " Dependents Selection " + dependentsSelection
                        + " Education Selection " + educationSelection
                        + " Self Employ Selection " + Self_EmployedSelection
                        + " Applicant Income Selection " + ApplicantIncomeSelection
                        + " Loan Amount Selection " + LoanAmountSelection
                        + " Credit History Selection " + Credit_HistorySelection
                        + " Property Area Selection " + Property_AreaSelection
                );
                loanPredictionMethod = APIClient.getClient().create(LoanPredictionMethod.class);
                Call<JsonObject> getPredictionCall = loanPredictionMethod.predict(
                        genderSelection,
                        marriedSelection,
                        dependentsSelection,
                        educationSelection,
                        Self_EmployedSelection,
                        ApplicantIncomeSelection,
                        LoanAmountSelection,
                        Credit_HistorySelection,
                        Property_AreaSelection
                );
                getPredictionCall.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        String data = String.valueOf(response.body());
                        JsonElement json = response.body().get("done");
                        Log.d("tushar","el "+json);
                        Log.d("tushar", "data " + data);
                        Intent i = new Intent(MainActivity.this, LoanPredictActivity.class);
                        i.putExtra("Predict", json.toString());
                        startActivity(i);
                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {
                        Log.d("tushar", "Error " + call);
                        Log.d("tushar", "Error " + t);
                        call.cancel();
                    }
                }


                );
                loaninfodata( );
            }
        })

        ;}

        private void loaninfodata(){
        String Genderinfo=genderText.getText().toString();
            String Marriedinfo=marriedText.getText().toString();
            String Dependentsinfo=dependentsText.getText().toString();
            String Educationinfo=educationText.getText().toString();
            String Self_Employedinfo=Self_EmployedText.getText().toString();
            String ApplicantIncomeinfo=ApplicantIncomeText.getText().toString();
            String LoanAmountinfo=LoanAmountText.getText().toString();
            String Credit_Historyinfo=Credit_HistoryText.getText().toString();
            String Property_Areainfo=Property_AreaText.getText().toString();




         LoanInfoUplode info = new LoanInfoUplode(Genderinfo,Marriedinfo,Dependentsinfo,Educationinfo,Self_Employedinfo,ApplicantIncomeinfo,LoanAmountinfo,Credit_Historyinfo,Property_Areainfo);
            loanInforef.push().setValue(info);
            Toast.makeText(MainActivity.this, "data inserted", Toast.LENGTH_SHORT).show();
        }


    }
