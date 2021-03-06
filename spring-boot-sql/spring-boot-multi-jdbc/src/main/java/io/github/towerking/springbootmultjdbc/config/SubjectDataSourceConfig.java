package io.github.towerking.springbootmultjdbc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
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

    @Bean
    @Resource
    public PlatformTransactionManager subjectTxManager(DataSource subjectDataSource) {
        return new DataSourceTransactionManager(subjectDataSource);
    }

}
