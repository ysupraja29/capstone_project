package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

public class common_college_pojo  implements Comparable<common_college_pojo>,Serializable {
    @SerializedName("college_id")
    private String college_id;
     @SerializedName("institute_name")
    private String institute_name;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("overall_score")
    private String overall_score;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getOverall_score() {
        return overall_score;
    }

    public void setOverall_score(String overall_score) {
        this.overall_score = overall_score;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInstitute_name() {
        return institute_name;
    }

    public void setInstitute_name(String institute_name) {
        this.institute_name = institute_name;
    }

    public String getCollege_id() {
        return college_id;
    }

    public void setCollege_id(String college_id) {
        this.college_id = college_id;
    }

    @SerializedName("rank")
    private int rank;


    @Override
    public int compareTo(common_college_pojo o) {
        int compareage=((common_college_pojo)o).getRank();
        /* For Ascending order*/
        return this.rank-compareage;
          /* For Descending order do like this */
        //return compareage-this.studentage;
    }
}
