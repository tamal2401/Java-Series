package com.demo.commonslogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingUtil {

    public static final String DASHBOARD_SERVICE_LOGGER = "dashboard-service-logger";

    private static final Logger log = LoggerFactory.getLogger(DASHBOARD_SERVICE_LOGGER);

    private LoggingUtil() {
    }

    public static void logTiming(String logMessage, Object... params){
        if(log.isDebugEnabled()){
            log.debug(logMessage, params);
            return;
        }
        log.info(logMessage, params);
    }
}
