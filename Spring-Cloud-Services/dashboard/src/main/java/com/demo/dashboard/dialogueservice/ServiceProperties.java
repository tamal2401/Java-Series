package com.demo.dashboard.dialogueservice;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
public class ServiceProperties {
    public ServiceDetails motivation;
    public ServiceDetails insult;

    public ServiceDetails getMotivation() {
        return motivation;
    }

    public void setMotivation(ServiceDetails motivation) {
        this.motivation = motivation;
    }

    public ServiceDetails getInsult() {
        return insult;
    }

    public void setInsult(ServiceDetails insult) {
        this.insult = insult;
    }

    @Override
    public String toString() {
        return "ServiceProperties{" +
                "motivation=" + motivation +
                ", insult=" + insult +
                '}';
    }

    @Validated
    public static class ServiceDetails {
        @NotBlank
        private String api;
        private int readtimeout;
        private int requesttimeout;

        public String getApi() {
            return api;
        }

        public void setApi(String api) {
            this.api = api;
        }

        public int getReadtimeout() {
            return readtimeout;
        }

        public void setReadtimeout(int readtimeout) {
            this.readtimeout = readtimeout;
        }

        public int getRequesttimeout() {
            return requesttimeout;
        }

        public void setRequesttimeout(int requesttimeout) {
            this.requesttimeout = requesttimeout;
        }

        @Override
        public String toString() {
            return "ServiceDetails{" +
                    "api='" + api + '\'' +
                    ", readtimeout=" + readtimeout +
                    ", requesttimeout=" + requesttimeout +
                    '}';
        }
    }
}
