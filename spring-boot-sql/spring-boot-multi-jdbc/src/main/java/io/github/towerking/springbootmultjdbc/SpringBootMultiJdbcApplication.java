package io.github.towerking.springbootmultjdbc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Objects;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class })
@Slf4j
public class SpringBootMultiJdbcApplication implements ApplicationRunner {

    @Autowired
    private JdbcTemplate subjectTemplate;

    @Autowired
    private JdbcTemplate studentTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMultiJdbcApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("current connection {}", Objects.requireNonNull(subjectTemplate.getDataSource()).getConnection().toString());

        String sql = "create table subject (id int(11) not null, name varchar(50) not null)";
        subjectTemplate.execute(sql);

        subjectTemplate.execute("insert into subject (id, name) values (1, '语文')");

        subjectTemplate.queryForList("select * from subject").forEach(s -> log.info("Subject {}", s));

        log.info("student connection {}", Objects.requireNonNull(studentTemplate.getDataSource()).getConnection().toString());
    }
}
