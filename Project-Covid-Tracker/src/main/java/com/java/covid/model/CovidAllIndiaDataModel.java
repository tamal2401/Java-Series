package com.java.covid.model;

import java.util.List;

public class CovidAllIndiaDataModel {

    private List<CovidDataPerState> stateData;
    private int totalEffectedCount;
    private int totalCured;
    private int totalDeath;

    public List<CovidDataPerState> getStateData() {
        return stateData;
    }

    public void setStateData(List<CovidDataPerState> stateData) {
        this.stateData = stateData;
    }

    public int getTotalEffectedCount() {
        return totalEffectedCount;
    }

    public void setTotalEffectedCount(int totalEffectedCount) {
        this.totalEffectedCount = totalEffectedCount;
    }

    public int getTotalCured() {
        return totalCured;
    }

    public void setTotalCured(int totalCured) {
        this.totalCured = totalCured;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }
}
