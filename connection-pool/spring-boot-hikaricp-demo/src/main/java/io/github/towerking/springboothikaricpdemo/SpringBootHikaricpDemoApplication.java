package io.github.towerking.springboothikaricpdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@Slf4j
public class SpringBootHikaricpDemoApplication implements ApplicationRunner {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootHikaricpDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        showConnection();
        showData();
    }

    private void showConnection() throws SQLException {
        log.info("connection: {}", dataSource.getConnection());
    }

    private void showData() {
        log.info("datasource: {}", jdbcTemplate.getDataSource());
        jdbcTemplate.queryForList("select * from example").forEach(ex -> {
            log.info("example: {}", ex);
        });
    }
}
