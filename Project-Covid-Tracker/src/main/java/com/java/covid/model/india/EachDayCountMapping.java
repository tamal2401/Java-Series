package com.java.covid.model.india;

public class EachDayCountMapping {
    private int count;
    private String date;

    public EachDayCountMapping(int count, String date) {
        this.count = count;
        this.date = date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "["+this.date+","+this.count+"]";
    }
}
