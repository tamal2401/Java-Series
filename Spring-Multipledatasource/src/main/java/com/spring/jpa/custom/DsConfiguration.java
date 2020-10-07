package com.spring.jpa.custom;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static java.util.Collections.singletonMap;


@Configuration
@ConfigurationProperties(prefix = "app.custom.datasource")
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "profileManagerFactory",
//        transactionManagerRef = "profileTransactionManager",
//        basePackages = "com.spring.jpa.custom.repository"
//)
//@EnableTransactionManagement
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

//    @Bean("CustomDatasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .url(this.url)
//                .username(this.username)
//                .password(this.password)
//                .build();
//    }
//
    @Bean("CustomTemplate")
    public JdbcTemplate getTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(
                DataSourceBuilder
                .create()
                .url(this.url)
                .username(this.username)
                .password(this.password)
                .build()
        );
        return template;
    }

//    @Bean(name = "profileManagerFactory")
//    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory(final EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(dataSource())
//                .packages("com.spring.jpa.custom.domain")
//                .persistenceUnit("firstDb")
//                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop"))
//                .build();
//    }
//
//    @Bean(name = "profileTransactionManager")
//    public PlatformTransactionManager firstTransactionManager(@Qualifier("profileManagerFactory")
//                                                                      EntityManagerFactory firstEntityManagerFactory) {
//        return new JpaTransactionManager(firstEntityManagerFactory);
//    }
}
