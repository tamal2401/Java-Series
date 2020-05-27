package com.java.covid.model;

public class CovidDataPerState {

    private int no;
    private String state;
    private int totalCasesIndian = 0;
    private int totalCasesForeign = 0;
    private int cured = 0;
    private int death = 0;

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

    public int getTotalCasesIndian() {
        return totalCasesIndian;
    }

    public void setTotalCasesIndian(int totalCasesIndian) {
        this.totalCasesIndian = totalCasesIndian;
    }

    public int getTotalCasesForeign() {
        return totalCasesForeign;
    }

    public void setTotalCasesForeign(int totalCasesForeign) {
        this.totalCasesForeign = totalCasesForeign;
    }

    public int getTotal() {
        return this.totalCasesForeign+this.totalCasesIndian;
    }

    public int getCured() {
        return cured;
    }

    public void setCured(int cured) {
        this.cured = cured;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }
}
