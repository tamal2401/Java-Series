package com.demo.crud;

public class DataSaveException extends Exception {

    private static final long serialVersionUID = 1L;

    public DataSaveException() {
        super();
    }

    public DataSaveException(String message) {
        super(message);
    }
}
