package com.cloud.zuul.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class FallbackModel {

    private String serviceName;
    private String failureMessage;
    private String requestType;
    private String requestBody;
}
