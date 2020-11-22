package com.demo.dashboard.config.actuator.heartbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.lang.invoke.MethodHandles;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Endpoint(id = "heartbeat", enableByDefault = true)
public class HeartBeatEndpoint {
    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ReadOperation
    public HeartBeat invoke(){
        HeartBeat.Builder builder;
        try{
            builder = new HeartBeat.Builder()
                    .up()
                    .withDetails("HOST_NAME", InetAddress.getLocalHost().getHostName());
        }catch (UnknownHostException e){
            log.error("error getting hostname: "+e.getMessage());
            builder = new HeartBeat.Builder().down(e);
        }
        return builder.build();
    }
}
