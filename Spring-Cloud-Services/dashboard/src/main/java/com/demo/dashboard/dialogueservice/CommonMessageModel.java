package com.demo.dashboard.dialogueservice;

public class CommonMessageModel {
    private String message;
    private String createdBy;

    public CommonMessageModel(String message, String createdBy) {
        this.message = message;
        this.createdBy = createdBy;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "CommonMessageModel{" +
                "message='" + message + '\'' +
                ", createdBy='" + createdBy + '\'' +
                '}';
    }
}
