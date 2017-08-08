package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by janardhanyerranagu on 17/11/16.
 */

public class sub_metric_pojo implements Serializable {
    @SerializedName("sub_metric")
    private String sub_metric;

    public String getSub_metric_value() {
        return sub_metric_value;
    }

    public void setSub_metric_value(String sub_metric_value) {
        this.sub_metric_value = sub_metric_value;
    }

    public String getSub_metric() {
        return sub_metric;
    }

    public void setSub_metric(String sub_metric) {
        this.sub_metric = sub_metric;
    }

    @SerializedName("sub_metric_value")
    private String sub_metric_value;

}
