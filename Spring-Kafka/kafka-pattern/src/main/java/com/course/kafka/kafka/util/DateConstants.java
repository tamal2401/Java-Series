package com.course.kafka.kafka.util;

import java.time.format.DateTimeFormatter;

public interface DateConstants {

    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
}
