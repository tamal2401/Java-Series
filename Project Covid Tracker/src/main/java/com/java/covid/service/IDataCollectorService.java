package com.java.covid.service;

import com.java.covid.model.CovidAllIndiaDataModel;

import java.io.IOException;
import java.util.List;

public interface IDataCollectorService {

    public void getCovidData() throws IOException;

    public void getIndianStats() throws IOException;

    CovidAllIndiaDataModel getAllIndiaStats();
}
