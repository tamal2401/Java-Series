package com.cloud.zuul.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FallbackModel {

    private String serviceName;
    private String failureMessage;
    private String requestType;
    private Object requestBody;
}
