package com.java.covid.model;

public class CovidStatModel {

    private String state;
    private String country;
    private int latesCases;
    private int changeInCasualties;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatesCases() {
        return latesCases;
    }

    public void setLatesCases(int latesCases) {
        this.latesCases = latesCases;
    }

    public int getChangeInCasualties() {
        return changeInCasualties;
    }

    public void setChangeInCasualties(int changeInCasualties) {
        this.changeInCasualties = changeInCasualties;
    }

    @Override
    public String toString() {
        return "CovidStatModel{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", latesCases=" + latesCases +
                ", changeInCasualties=" + changeInCasualties +
                '}';
    }
}
