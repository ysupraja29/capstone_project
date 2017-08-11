package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by janardhanyerranagu on 17/11/16.
 */

public class GoPojo implements Serializable {
    @SerializedName("go_state_rank")
    private String go_state_rank;
    @SerializedName("go_score")
    private String go_score;
    @SerializedName("go_rank")
    private int go_rank;
    @SerializedName("go_sub")
    private ArrayList<SubMetricPojo> go_sub;

    public ArrayList<SubMetricPojo> getGo_sub() {
        return go_sub;
    }

    public void setGo_sub(ArrayList<SubMetricPojo> go_sub) {
        this.go_sub = go_sub;
    }

    public int getGo_rank() {
        return go_rank;
    }

    public void setGo_rank(int go_rank) {
        this.go_rank = go_rank;
    }

    public String getGo_score() {
        return go_score;
    }

    public void setGo_score(String go_score) {
        this.go_score = go_score;
    }

    public String getGo_state_rank() {
        return go_state_rank;
    }

    public void setGo_state_rank(String go_state_rank) {
        this.go_state_rank = go_state_rank;
    }
}
