package com.demo.schedule.mdoel;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class TaskResponse {
    private String errorCode;
    private String errorMsg;
    private String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    private Object data;

    public TaskResponse(Object data) {
        this.data = data;
    }

    public TaskResponse(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
