package com.example.loaneligibilityprediction.utils;

import com.example.loaneligibilityprediction.Prediction;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoanPredictionMethod {
    @GET("/api/unknown")
    Call<String> data();

    @POST("predict")
    Call<String> getPredictionOfLoan(@Body Prediction prediction);

    @FormUrlEncoded
    @POST("predict")
    Call<JsonObject> predict(@Field("Gender")
                                 int Gender,
                             @Field("Married")
                                 int Married,
                             @Field("Dependents")
                                 int Dependents,
                             @Field("Education")
                                 int Education,
                             @Field("Self_Employed")
                                 int Self_Employed,
                             @Field("ApplicantIncome")
                                 int ApplicantIncome,
                             @Field("LoanAmount")
                                 int LoanAmount,
                             @Field("Credit_History")
                                 int Credit_History,
                             @Field("Property_Area")
                                 int Property_Area
    );
}
