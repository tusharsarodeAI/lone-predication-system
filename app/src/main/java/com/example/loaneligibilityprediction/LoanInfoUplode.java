package com.example.loaneligibilityprediction;

public class LoanInfoUplode  {

    String Gender;
    String Married;
    String Dependents;
    String Education;
    String Self_Employed;
    String ApplicantIncome;
    String LoanAmount;
    String Credit_History;
    String Property_Area;

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getMarried() {
        return Married;
    }

    public void setMarried(String married) {
        Married = married;
    }

    public String getDependents() {
        return Dependents;
    }

    public void setDependents(String dependents) {
        Dependents = dependents;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getSelf_Employed() {
        return Self_Employed;
    }

    public void setSelf_Employed(String self_Employed) {
        Self_Employed = self_Employed;
    }

    public String getApplicantIncome() {
        return ApplicantIncome;
    }

    public void setApplicantIncome(String applicantIncome) {
        ApplicantIncome = applicantIncome;
    }

    public String getLoanAmount() {
        return LoanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        LoanAmount = loanAmount;
    }

    public String getCredit_History() {
        return Credit_History;
    }

    public void setCredit_History(String credit_History) {
        Credit_History = credit_History;
    }

    public String getProperty_Area() {
        return Property_Area;
    }

    public void setProperty_Area(String property_Area) {
        Property_Area = property_Area;
    }

    public LoanInfoUplode(String gender, String married, String dependents, String education, String self_Employed, String applicantIncome, String loanAmount, String credit_History, String property_Area) {
        Gender = gender;
        Married = married;
        Dependents = dependents;
        Education = education;
        Self_Employed = self_Employed;
        ApplicantIncome = applicantIncome;
        LoanAmount = loanAmount;
        Credit_History = credit_History;
        Property_Area = property_Area;
    }
}
