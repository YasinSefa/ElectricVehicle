package com.example.electricvehicle.Connect;

import com.google.gson.annotations.SerializedName;

public class Response {
    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int result_code;

    //create getter method


    public String getStatus() {
        return status;
    }

    public int getResult_code() {
        return result_code;
    }

}
