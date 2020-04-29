package com.java.jobscraper.pojo;

import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class JobDetailsResponse {

    @NonNull
    private String searchCriteria;
    private List<EachJobDetails> listOfJobDetails;
}
