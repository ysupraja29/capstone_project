package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by janardhanyerranagu on 17/11/16.
 */

public class oi_pojo implements Serializable {
    @SerializedName("oi_state_rank")
    private String oi_state_rank;
    @SerializedName("oi_score")
    private String oi_score;
    @SerializedName("oi_rank")
    private int oi_rank;


    public ArrayList<sub_metric_pojo> getOi_sub() {
        return oi_sub;
    }

    public void setOi_sub(ArrayList<sub_metric_pojo> oi_sub) {
        this.oi_sub = oi_sub;
    }

    public int getOi_rank() {
        return oi_rank;
    }

    public void setOi_rank(int oi_rank) {
        this.oi_rank = oi_rank;
    }

    public String getOi_score() {
        return oi_score;
    }

    public void setOi_score(String oi_score) {
        this.oi_score = oi_score;
    }

    public String getOi_state_rank() {
        return oi_state_rank;
    }

    public void setOi_state_rank(String oi_state_rank) {
        this.oi_state_rank = oi_state_rank;
    }

    @SerializedName("oi_sub")
    private ArrayList<sub_metric_pojo> oi_sub;
}
