package com.demo.dashboard.dialogueservice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(reason = "Service is not responding", code = HttpStatus.INTERNAL_SERVER_ERROR)
public class UnresponsiveServiceException extends Exception {

    public UnresponsiveServiceException() {
        super();
    }

    public UnresponsiveServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnresponsiveServiceException(String message) {
        super(message);
    }
}
