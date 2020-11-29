package com.demo.dashboard.dialogueservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MotivationModel implements Serializable {

    @JsonProperty(value = "qotd_date")
    private String quoteDate;

    private Quote quote;

    public MotivationModel() {
    }

    public MotivationModel(String quoteDate, Quote quote) {
        this.quoteDate = quoteDate;
        this.quote = quote;
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public Quote getQuote() {
        return quote;
    }

    @Override
    public String toString() {
        return "MotivationModel{" +
                "quoteDate='" + quoteDate + '\'' +
                ", quote=" + quote +
                '}';
    }
}
