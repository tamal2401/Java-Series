package com.kafka.demo.order.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalResponse {
    private int errorCode;
    private String errorMsg;
    private String timeStamp;
    private String eventId;
    private Object data;
    private String resMsg;

    public GlobalResponse(Object data) {
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.data = data;
    }

    public GlobalResponse(Object data, String resMsg) {
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.data = data;
        this.resMsg = resMsg;
    }

    public GlobalResponse(int errorCode, String errorMsg) {
        this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
