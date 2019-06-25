package io.github.towerking.springbootmultjdbc.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

    @Bean
    public JdbcTemplate subjectTemplate(@Qualifier("subjectDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public JdbcTemplate studentTemplate(@Qualifier("studentDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
