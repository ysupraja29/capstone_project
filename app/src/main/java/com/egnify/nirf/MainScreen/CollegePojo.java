package com.egnify.nirf.MainScreen;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CollegePojo implements Serializable {
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
    private TlrPojo tlr;
    @SerializedName("rpc")
    private RpcPojo rpc;
    @SerializedName("go")
    private GoPojo go;
    @SerializedName("oi")
    private OiPojo oi;
    @SerializedName("overall_score")
    private String overall_score;
    @SerializedName("rank")
    private int rank;
    @SerializedName("perception")
    private PerceptionPojo perception;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public PerceptionPojo getPerception() {
        return perception;
    }

    public void setPerception(PerceptionPojo perception) {
        this.perception = perception;
    }

    public OiPojo getOi() {
        return oi;
    }

    public void setOi(OiPojo oi) {
        this.oi = oi;
    }

    public GoPojo getGo() {
        return go;
    }

    public void setGo(GoPojo go) {
        this.go = go;
    }

    public RpcPojo getRpc() {
        return rpc;
    }

    public void setRpc(RpcPojo rpc) {
        this.rpc = rpc;
    }

    public TlrPojo getTlr() {
        return tlr;
    }

    public void setTlr(TlrPojo tlr) {
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


}
