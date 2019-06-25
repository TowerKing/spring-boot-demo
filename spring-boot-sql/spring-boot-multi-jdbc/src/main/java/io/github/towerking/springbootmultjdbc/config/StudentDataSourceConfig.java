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
public class StudentDataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.student")
    @Bean
    public DataSourceProperties studentDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource studentDataSource() {
        DataSourceProperties dataSourceProperties = studentDataSourceProperties();
        log.info("student datasource {}", dataSourceProperties.getUrl());
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean
    @Resource
    public PlatformTransactionManager studentTxManager(DataSource studentDataSource) {
        return new DataSourceTransactionManager(studentDataSource);
    }

}
