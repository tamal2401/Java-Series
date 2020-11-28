package com.demo.dashboard.dialogueservice;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@ConfigurationProperties(prefix = "external.service")
public class DsServiceConfiguration implements InitializingBean {

    public ServiceDetails motivation;
    public ServiceDetails insult;

    @Bean
    public OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggingRequestInterceptor())
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println(motivation);
        System.out.println(insult);
    }

    public static class ServiceDetails {
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
