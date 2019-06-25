package io.github.towerking.springbootmultjdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class SubjectDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.subject")
    @Bean
    public DataSourceProperties subjectDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource subjectDataSource() {
        DataSourceProperties dataSourceProperties = subjectDataSourceProperties();
        log.info("subject datasource {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

}
