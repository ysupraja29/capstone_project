package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class college_pojo implements Serializable {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @SerializedName("id")
    private Long id;
    @SerializedName("college_id")
    private String college_id;
    @SerializedName("institute_name")
    private String institute_name;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;
    @SerializedName("tlr")
    private tlr_pojo tlr;
    @SerializedName("rpc")
    private rpc_pojo rpc;
    @SerializedName("go")
    private go_pojo go;
    @SerializedName("oi")
    private oi_pojo oi;
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

    @SerializedName("rank")
    private int rank;

    public perception_pojo getPerception() {
        return perception;
    }

    public void setPerception(perception_pojo perception) {
        this.perception = perception;
    }

    public oi_pojo getOi() {
        return oi;
    }

    public void setOi(oi_pojo oi) {
        this.oi = oi;
    }

    public go_pojo getGo() {
        return go;
    }

    public void setGo(go_pojo go) {
        this.go = go;
    }

    public rpc_pojo getRpc() {
        return rpc;
    }

    public void setRpc(rpc_pojo rpc) {
        this.rpc = rpc;
    }

    public tlr_pojo getTlr() {
        return tlr;
    }

    public void setTlr(tlr_pojo tlr) {
        this.tlr = tlr;
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

    @SerializedName("perception")
    private perception_pojo perception;


}
