package com.cloud.Fallback.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FallbackModel {

    private String serviceName;
    private String failureMessage;
    private String requestType;
    private String requestBody;
}
