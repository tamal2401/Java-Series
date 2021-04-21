package com.cloud.Fallback.controller;

import com.cloud.Fallback.model.FallbackModel;
import com.cloud.Fallback.util.ServiceConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
public class FallbackController {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getName());

    @PostMapping(value = "/fallback/response",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getFallbackResponse(@RequestBody FallbackModel requestModel) throws JsonProcessingException {
        log.info("=========================================== Response Begin ==================================================");
        log.info(String.format("%s : service was down", requestModel.getServiceName()));
        log.info(String.format("Failure msg : %s", requestModel.getFailureMessage()));
        log.info(String.format("Failure request type : %s", requestModel.getRequestType()));
        log.info(String.format("Failed request body : %s", requestModel.getRequestBody()));
        log.info("============================================ Response End =================================================");

        return ServiceConstants.FALLBACK_MESSAGE;
    }
}
