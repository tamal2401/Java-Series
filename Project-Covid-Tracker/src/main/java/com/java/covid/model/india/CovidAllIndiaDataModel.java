package com.java.covid.model.india;

import java.util.List;

public class CovidAllIndiaDataModel {

    private List<CovidDataPerStateOfIndia> stateData;
    private int totalConfirmed;
    private int totalRecovered;
    private int totalDeath;
    private String lastUpdated;

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<CovidDataPerStateOfIndia> getStateData() {
        return stateData;
    }

    public void setStateData(List<CovidDataPerStateOfIndia> stateData) {
        this.stateData = stateData;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public int getTotalDeath() {
        return totalDeath;
    }

    public void setTotalDeath(int totalDeath) {
        this.totalDeath = totalDeath;
    }
}
