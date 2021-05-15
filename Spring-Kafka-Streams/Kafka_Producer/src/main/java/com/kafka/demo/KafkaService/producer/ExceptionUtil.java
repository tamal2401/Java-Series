package com.kafka.demo.KafkaService.producer;

public class ExceptionUtil {

    public static final String NEW_LINE = "\n";

    public static String getStackTrace(Throwable e) {
        StringBuilder builder = new StringBuilder();
        builder.append(NEW_LINE);
        builder.append(e.getMessage());
        builder.append(NEW_LINE);
        for(StackTraceElement each : e.getStackTrace()){
            builder.append(each.toString());
            builder.append(NEW_LINE);
        }
        return builder.toString();
    }
}
