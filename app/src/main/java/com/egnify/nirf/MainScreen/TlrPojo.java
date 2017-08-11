package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by janardhanyerranagu on 17/11/16.
 */

public class TlrPojo implements Serializable {
    @SerializedName("tlr_state_rank")
    private String tlr_state_rank;
    @SerializedName("tlr_score")
    private String tlr_score;
    @SerializedName("tlr_rank")
    private int tlr_rank;
    @SerializedName("tlr_sub")
    private ArrayList<SubMetricPojo> tlr_sub;

    public ArrayList<SubMetricPojo> getTlr_sub() {
        return tlr_sub;
    }

    public void setTlr_sub(ArrayList<SubMetricPojo> tlr_sub) {
        this.tlr_sub = tlr_sub;
    }

    public int getTlr_rank() {
        return tlr_rank;
    }

    public void setTlr_rank(int tlr_rank) {
        this.tlr_rank = tlr_rank;
    }

    public String getTlr_score() {
        return tlr_score;
    }

    public void setTlr_score(String tlr_score) {
        this.tlr_score = tlr_score;
    }

    public String getTlr_state_rank() {
        return tlr_state_rank;
    }

    public void setTlr_state_rank(String tlr_state_rank) {
        this.tlr_state_rank = tlr_state_rank;
    }
}
