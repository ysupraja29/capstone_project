package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by janardhanyerranagu on 17/11/16.
 */

public class RpcPojo implements Serializable {
    @SerializedName("rpc_state_rank")
    private String rpc_state_rank;
    @SerializedName("rpc_score")
    private String rpc_score;
    @SerializedName("rpc_rank")
    private int rpc_rank;
    @SerializedName("rpc_sub")
    private ArrayList<SubMetricPojo> rpc_sub;

    public ArrayList<SubMetricPojo> getRpc_sub() {
        return rpc_sub;
    }

    public void setRpc_sub(ArrayList<SubMetricPojo> rpc_sub) {
        this.rpc_sub = rpc_sub;
    }

    public int getRpc_rank() {
        return rpc_rank;
    }

    public void setRpc_rank(int rpc_rank) {
        this.rpc_rank = rpc_rank;
    }

    public String getRpc_score() {
        return rpc_score;
    }

    public void setRpc_score(String rpc_score) {
        this.rpc_score = rpc_score;
    }

    public String getRpc_state_rank() {
        return rpc_state_rank;
    }

    public void setRpc_state_rank(String rpc_state_rank) {
        this.rpc_state_rank = rpc_state_rank;
    }
}
