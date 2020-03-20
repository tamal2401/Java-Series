package com.java.covid.service;

import com.java.covid.model.CovidStatModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CovidDattaCollectorService implements IDataCollectorService {

    private static String DATA_URI = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    List<CovidStatModel> allStats = new ArrayList<>();

    @Override
    @Bean
    public void getCovidData() throws IOException {
        List<CovidStatModel> tempStats = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        String response = template.getForObject(DATA_URI, String.class);
        Reader reader = new StringReader(response);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        records.forEach(record -> {
            CovidStatModel model = new CovidStatModel();
            model.setState(record.get("Province/State"));
            model.setCountry(record.get("Country/Region"));
            model.setLatesCases(Integer.parseInt(record.get(record.size()-1)));
            model.setChangeInCasualties(Integer.parseInt(record.get(record.size()-1))-Integer.parseInt(record.get(record.size()-2)));
            tempStats.add(model);
        });
        this.allStats = tempStats;
    }
}
