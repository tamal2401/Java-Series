package com.spring.jpa.custom;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@Configuration
@ConfigurationProperties(prefix = "app.custom.datasource")
public class DsConfiguration {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Bean("CustomTemplate")
    public JdbcTemplate getTemplate() throws SQLException {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(
                DataSourceBuilder
                .create()
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .build()
        );
        template.getDataSource().getConnection().setAutoCommit(false);
        return template;
    }

}
