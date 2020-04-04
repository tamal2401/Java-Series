package com.java.covid.service;

import com.java.covid.model.india.CovidAllIndiaDataModel;

import java.io.IOException;

public interface IDataCollectorService {

    public void getCovidData() throws IOException;

    public void getIndianStats() throws IOException;

    CovidAllIndiaDataModel getAllIndiaStats();
}
