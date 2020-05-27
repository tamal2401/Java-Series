package com.java.covid.model.india;

public class CovidDataPerStateOfIndia {

    private int no;
    private String state;
    private int active;
    private int confirmed;
    private int deaths;
    private String lastupdatedtime;
    private int recovered;
    private DeltaInformation delta;
    private int deltaconfirmed;
    private int deltadeaths;
    private int deltarecovered;
    private String statecode;

    public int getDeltaconfirmed() {
        return deltaconfirmed;
    }

    public void setDeltaconfirmed(int deltaconfirmed) {
        this.deltaconfirmed = deltaconfirmed;
    }

    public int getDeltadeaths() {
        return deltadeaths;
    }

    public void setDeltadeaths(int deltadeaths) {
        this.deltadeaths = deltadeaths;
    }

    public int getDeltarecovered() {
        return deltarecovered;
    }

    public void setDeltarecovered(int deltarecovered) {
        this.deltarecovered = deltarecovered;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public String getLastupdatedtime() {
        return lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime) {
        this.lastupdatedtime = lastupdatedtime;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public DeltaInformation getDelta() {
        return delta;
    }

    public void setDelta(DeltaInformation delta) {
        this.delta = delta;
    }
}
