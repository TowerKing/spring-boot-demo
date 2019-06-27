package io.github.towerking.springbootdeclarativetransaction;

import io.github.towerking.springbootdeclarativetransaction.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@Slf4j
public class SpringBootDeclarativeTransactionApplication implements ApplicationRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDeclarativeTransactionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        userService.insertUser();
        log.info("Id = 1 count: {}", jdbcTemplate.queryForObject("select count(1) from user where id = 1", Long.class));

        try {
            userService.insertThenRollback();
        } catch (Exception e) {
            log.info("Id = 2 count: {}", jdbcTemplate.queryForObject("select count(1) from user where id = 2", Long.class));
        }

        try {
            userService.invokeInsertThenRollback();
        } catch (Exception e) {
            log.info("Id = 2 count: {}", jdbcTemplate.queryForObject("select count(1) from user where id = 2", Long.class));
        }

    }
}
