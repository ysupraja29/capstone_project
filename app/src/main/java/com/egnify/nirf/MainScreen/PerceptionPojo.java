package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by janardhanyerranagu on 17/11/16.
 */

public class PerceptionPojo implements Serializable {
    @SerializedName("perception_state_rank")
    private String perception_state_rank;
    @SerializedName("perception_score")
    private String perception_score;
    @SerializedName("perception_rank")
    private int perception_rank;
    @SerializedName("perception_sub")
    private ArrayList<SubMetricPojo> perception_sub;

    public ArrayList<SubMetricPojo> getPerception_sub() {
        return perception_sub;
    }

    public void setPerception_sub(ArrayList<SubMetricPojo> perception_sub) {
        this.perception_sub = perception_sub;
    }

    public int getPerception_rank() {
        return perception_rank;
    }

    public void setPerception_rank(int perception_rank) {
        this.perception_rank = perception_rank;
    }

    public String getPerception_score() {
        return perception_score;
    }

    public void setPerception_score(String perception_score) {
        this.perception_score = perception_score;
    }

    public String getPerception_state_rank() {
        return perception_state_rank;
    }

    public void setPerception_state_rank(String perception_state_rank) {
        this.perception_state_rank = perception_state_rank;
    }
}
