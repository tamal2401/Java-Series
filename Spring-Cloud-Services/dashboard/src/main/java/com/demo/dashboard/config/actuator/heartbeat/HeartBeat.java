package com.demo.dashboard.config.actuator.heartbeat;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.boot.actuate.health.Status;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class HeartBeat {

    private final Status status;
    private final Map<String, Object> details;

    public HeartBeat(HeartBeat.Builder builder) {
        Assert.notNull(builder, "HeartBeat Builder can not be null");
        this.status = builder.status;
        this.details = builder.details;
    }

    public Status getStatus(){
        return this.status;
    }

    public Map<String, Object> getDetails(){
        return this.details;
    }

    @Override
    public String toString(){
        return  this.getStatus()+":"+this.getDetails();
    }

    public static class Builder {
        private Status status;
        private Map<String, Object> details;

        public Builder(Status status, Map<String, Object> details) {
            this.status = status;
            this.details = details;
        }

        public Builder() {
            this.status = Status.UNKNOWN;
            this.details = new LinkedHashMap<>();
        }

        public HeartBeat.Builder withException(Exception exception) {
            Assert.notNull(exception, "Exception can not be null");
            return this.withDetails("error",
                    exception.getClass().getName() + " : " + exception.getMessage());
        }

        public HeartBeat.Builder withDetails(String msg, Object value) {
            Assert.notNull(msg, "key can not be null");
            Assert.notNull(value, "value can not be null");
            this.details.put(msg, value);
            return this;
        }

        public HeartBeat.Builder up(){
            return this.status(Status.UP);
        }

        public HeartBeat.Builder down(Exception ex){
            return this.down().withException(ex);
        }

        public HeartBeat.Builder down(){
            return this.status(Status.DOWN);
        }

        public HeartBeat.Builder status(String status){
            return this.status(new Status(status));
        }

        public HeartBeat.Builder status(Status status){
            this.status = status;
            return this;
        }

        public HeartBeat build(){
            return new HeartBeat(this);
        }
    }
}
