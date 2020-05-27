package com.java.jobscraper.pojo;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class EachJobDetails {

    private String jobId;
    private String jobUrl;
    private String jobLocation;
    private String requiredExp;
}
