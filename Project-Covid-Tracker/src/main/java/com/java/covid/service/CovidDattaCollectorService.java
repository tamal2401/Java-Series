package com.java.covid.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.covid.model.india.CovidAllIndiaDataModel;
import com.java.covid.model.india.CovidDataPerStateOfIndia;
import com.java.covid.model.CovidStatModel;
import com.java.covid.model.timeseries.TimeSeriesDataModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CovidDattaCollectorService {

    @Autowired
    ObjectMapper mapper;

    private static final String TIME_SERIES_DATA_URL = "https://api.covid19india.org/data.json";
    private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private String INDIAN_DATA_URL = "https://www.mohfw.gov.in/";

    List<CovidStatModel> allStats = new ArrayList<>();
    CovidAllIndiaDataModel consolidatedDataOfIndia = new CovidAllIndiaDataModel();
    public List<TimeSeriesDataModel> seriesModel = new ArrayList<>();


    public List<CovidStatModel> getGlobalStats() {
        return allStats;
    }

    //@Async("myExecutor")
    @Scheduled(cron = "0 0 */4 * * *")
    @Bean
    public void getCovidData() throws IOException {
        List<CovidStatModel> tempStats = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        String response = template.getForObject(DATA_URL, String.class);
        Reader reader = new StringReader(response);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().withNullString("").parse(reader);
        records.forEach(record -> {
            CovidStatModel model = new CovidStatModel();
            model.setState(record.get("Province/State"));
            model.setCountry(record.get("Country/Region"));
            model.setLatestCases(getCellValue(record, 1));
            model.setChangeInCasualties(getCellValue(record, 1) - getCellValue(record, 2));
            tempStats.add(model);
        });
        List<CovidStatModel> collectionObj = sortDataCountryWise(tempStats);
        this.allStats = collectionObj;
        System.out.println("global data fetched");
    }

    private int getCellValue(CSVRecord record, int lastCellIndex) {
        int countt = 0;
        String cellValue = record.get(record.size() - lastCellIndex);
        if (null == cellValue) {
            cellValue = "0";
        }
        return Integer.parseInt(cellValue);
    }

    private List<CovidStatModel> sortDataCountryWise(List<CovidStatModel> tempStats) {

        Map<String, TempDataPerCountry> tempMap = new HashMap<>();


        for (CovidStatModel stat : tempStats) {
            if (tempMap.containsKey(stat.getCountry())) {
                TempDataPerCountry countryValue = tempMap.get(stat.getCountry());
                countryValue.incrementCurrentCount(stat.getLatestCases());
                countryValue.incrementChangeInDelta(stat.getChangeInCasualties());
            } else {
                tempMap.put(stat.getCountry(), new TempDataPerCountry(stat.getLatestCases(), stat.getChangeInCasualties()));
            }
        }

        // Map<String, TempDataPerCountry> sortedMap = new TreeMap<>(tempMap);

        List<CovidStatModel> collectionObj = tempMap.entrySet().stream().map(each -> {
            CovidStatModel model = new CovidStatModel();
            model.setChangeInCasualties(each.getValue().getChangeinLastoneDay());
            model.setCountry(each.getKey());
            model.setLatestCases(each.getValue().getEffectedCount());
            return model;
        }).sorted(Comparator.comparingInt(CovidStatModel::getLatestCases).reversed()).collect(Collectors.toList());

        return collectionObj;
    }

    class TempDataPerCountry {
        private int effectedCount;
        private int changeinLastoneDay;

        public TempDataPerCountry(int effectedCount, int changeinLastoneDay) {
            this.effectedCount = effectedCount;
            this.changeinLastoneDay = changeinLastoneDay;
        }

        public int getEffectedCount() {
            return effectedCount;
        }

        public void setEffectedCount(int effectedCount) {
            this.effectedCount = effectedCount;
        }

        public int getChangeinLastoneDay() {
            return changeinLastoneDay;
        }

        public void setChangeinLastoneDay(int changeinLastoneDay) {
            this.changeinLastoneDay = changeinLastoneDay;
        }

        public void incrementCurrentCount(int latestCases) {
            this.effectedCount = this.effectedCount + latestCases;
        }

        public void incrementChangeInDelta(int changeInCasualties) {
            this.changeinLastoneDay = this.changeinLastoneDay + changeInCasualties;
        }
    }

    public CovidAllIndiaDataModel getAllIndianStats() {
        return this.consolidatedDataOfIndia;
    }

    public List<CovidStatModel> getGlobaldata() {
        return this.allStats;
    }

    @Bean
    @Scheduled(cron = "0 0 */4 * * *")
    public String getTimeSeriesData() {
        List<CovidStatModel> tempStats = new ArrayList<>();
        List<TimeSeriesDataModel> listOfTimeSeriesData = new ArrayList<>();
        RestTemplate template = new RestTemplate();
        String response = template.getForObject(TIME_SERIES_DATA_URL, String.class);
        ObjectMapper mapper = new ObjectMapper();
        if (response != null && !StringUtils.isBlank(response)) {
            JSONObject obj = new JSONObject(response);
            getIndianStats(obj);
            extractTimeSeriesDataFromJson(listOfTimeSeriesData, obj);
            // createTimeSeriesChart(listOfTimeSeriesData);
        } else {

        }
        return response;
    }

    private void getIndianStats(JSONObject obj) {
        List<CovidDataPerStateOfIndia> allStateData = new ArrayList<>();
        JSONArray jArray = obj.getJSONArray("statewise");
        jArray.forEach(each -> {
            StringReader reader = new StringReader(each.toString());
            try {
                CovidDataPerStateOfIndia temp = mapper.readValue(reader, CovidDataPerStateOfIndia.class);
                if (!temp.getState().toLowerCase().equalsIgnoreCase("total")) {
                    allStateData.add(temp);
                }
            } catch (IOException e) {
                System.out.println("error occured while deserializing :" + e.getMessage());
            }
        });
        int totalConfirmed = allStateData.stream().mapToInt(each -> each.getConfirmed()).sum();
        int totaldeaths = allStateData.stream().mapToInt(each -> each.getDeaths()).sum();
        int totalRecovered = allStateData.stream().mapToInt(each -> each.getRecovered()).sum();

        this.consolidatedDataOfIndia.setStateData(allStateData);
        this.consolidatedDataOfIndia.setTotalConfirmed(totalConfirmed);
        this.consolidatedDataOfIndia.setTotalDeath(totaldeaths);
        this.consolidatedDataOfIndia.setTotalRecovered(totalRecovered);
        this.consolidatedDataOfIndia.setLastUpdated(getCurrentTime());
    }

    private void extractTimeSeriesDataFromJson(List<TimeSeriesDataModel> listOfTimeSeriesData, JSONObject obj) {
        JSONArray array = obj.getJSONArray("cases_time_series");
        //array.remove(array.length() - 1);
        array.forEach(each -> {
            //TimeSeriesDataModel temp = gson.fromJson(each.toString(), TimeSeriesDataModel.class);
            StringReader reader = new StringReader(each.toString());
            try {
                TimeSeriesDataModel temp = mapper.readValue(reader, TimeSeriesDataModel.class);
                listOfTimeSeriesData.add(temp);
            } catch (IOException e) {
                System.out.println("error occured while deserializing :" + e.getMessage());
            }
        });
        this.seriesModel = listOfTimeSeriesData;
    }

    private void createTimeSeriesChart(List<TimeSeriesDataModel> listOfTimeSeriesData) {
        if (listOfTimeSeriesData.size() > 0) {
            DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();

            listOfTimeSeriesData.stream().forEach(each -> line_chart_dataset.addValue(each.getTotalconfirmed(), "Effected Count", each.getDate()));
            listOfTimeSeriesData.stream().forEach(each -> line_chart_dataset.addValue(each.getTotalrecovered(), "Total Recovered", each.getDate()));

            JFreeChart lineChartObject = ChartFactory.createLineChart(
                    "Effected Count VS Time", "Time",
                    "Effected Count",
                    line_chart_dataset, PlotOrientation.VERTICAL,
                    true, true, false);

            CategoryPlot plot = lineChartObject.getCategoryPlot();
            LineAndShapeRenderer renderer = new LineAndShapeRenderer();

            renderer.setSeriesPaint(0, Color.ORANGE);
            renderer.setSeriesPaint(1, Color.blue);

            // sets thickness for series (using strokes)
            renderer.setSeriesStroke(0, new BasicStroke(2.0f));
            renderer.setSeriesStroke(1, new BasicStroke(2.0f));

            plot.setRenderer(renderer);
            plot.setBackgroundPaint(Color.white);
            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.gray);

            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.gray);

            // To configure the Y Axis based on the interval
            ValueAxis axis = plot.getRangeAxis();
            //((NumberAxis) axis).setTickUnit(new NumberTickUnit(50));
            ((NumberAxis) axis).setNumberFormatOverride(new DecimalFormat() {
                public StringBuffer format(double number, StringBuffer toAppendTo, FieldPosition pos) {
                    return toAppendTo.append((int) (number / 100));
                }
            });
            axis.setTickLabelsVisible(true);
            plot.setRangeAxis(axis);
            plot.configureRangeAxes();

            CategoryAxis xAxis = plot.getDomainAxis();

            plot.configureDomainAxes();

            int width = 800;    /* Width of the image */
            int height = 400;   /* Height of the image */
            File lineChartFile = new File("src/main/resources/LineCharts/effectedount_vs_time.jpeg");
            try {
                ChartUtilities.saveChartAsJPEG(lineChartFile, lineChartObject, width, height);
            } catch (IOException e) {
                System.out.println("Error occured while saving Effected Count VS Time graph chart : " + e.getMessage());
            }
        }
    }

    public String getCurrentTime() {
        Date date = new Date();
        String strDateFormat = "dd MMM hh:mm a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
        String formattedDate = dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
        return formattedDate;
    }
}
