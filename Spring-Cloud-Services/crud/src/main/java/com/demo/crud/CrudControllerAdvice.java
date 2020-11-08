package com.demo.crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CrudControllerAdvice {

    @ExceptionHandler(DataSaveException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    String handleResourceNotFound(final DataSaveException exception,
                                             final HttpServletRequest request) {
        String error = "Exception occurred while saving data in DB";
        return error;
    }
}
