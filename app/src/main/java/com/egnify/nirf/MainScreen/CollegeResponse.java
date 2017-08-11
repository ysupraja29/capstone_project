package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CollegeResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("college_details")
    private List<CollegePojo> college_details;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<CollegePojo> getCollege_details() {
        return college_details;
    }

    public void setCollege_details(List<CollegePojo> college_details) {
        this.college_details = college_details;
    }
}
