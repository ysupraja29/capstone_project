package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class college_response {
    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @SerializedName("error")
    private String error;

    public List<college_pojo> getCollege_details() {
        return college_details;
    }

    public void setCollege_details(List<college_pojo> college_details) {
        this.college_details = college_details;
    }

    @SerializedName("college_details")
    private List<college_pojo> college_details;
}
