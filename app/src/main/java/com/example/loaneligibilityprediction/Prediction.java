package com.example.loaneligibilityprediction;

import com.google.gson.annotations.SerializedName;

public class Prediction {

    @SerializedName("Gender")
    int Gender;
    @SerializedName("Married")
    int Married;
    @SerializedName("Dependents")
    int Dependents;
    @SerializedName("Education")
    int Education;
    @SerializedName("Self_Employed")
    int Self_Employed;
    @SerializedName("ApplicantIncome")
    int ApplicantIncome;
    @SerializedName("LoanAmount")
    int LoanAmount;
    @SerializedName("Credit_History")
    int Credit_History;
    @SerializedName("Property_Area")
    int Property_Area;

    Prediction(
            int Gender,
            int Married,
            int Dependents,
            int Education,
            int Self_Employed,
            int ApplicantIncome,
            int LoanAmount,
            int Credit_History,
            int Property_Area
    ) {
        this.Gender = Gender;
        this.Married = Married;
        this.Dependents = Dependents;
        this.Education = Education;
        this.Self_Employed = Self_Employed;
        this.ApplicantIncome = ApplicantIncome;
        this.LoanAmount = LoanAmount;
        this.Credit_History = Credit_History;
        this.Property_Area = Property_Area;
    }
}
