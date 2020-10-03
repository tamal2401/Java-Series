package com.spring.jpa.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class DsConfiguration {

    @ConfigurationProperties(prefix = "app.custom.datasource")
    @Bean
    @Primary // this will override the datasource autoconfiguration and use your own everywhere
    public DataSource dataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
}
