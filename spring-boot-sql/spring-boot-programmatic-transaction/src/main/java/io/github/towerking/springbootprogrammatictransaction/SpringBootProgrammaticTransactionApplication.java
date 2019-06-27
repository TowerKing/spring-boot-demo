package io.github.towerking.springbootprogrammatictransaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@SpringBootApplication
@Slf4j
public class SpringBootProgrammaticTransactionApplication implements ApplicationRunner {

    @Autowired
    private TransactionTemplate transactionTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProgrammaticTransactionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("before transaction: {}", getTotal());
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                jdbcTemplate.execute("insert into user (id, name, age) values (1, 'Hello World', 18)");
                log.info("in transaction: {}", getTotal());
                transactionStatus.setRollbackOnly();
            }
        });
        log.info("after transaction: {}", getTotal());
    }

    private long getTotal() {
        return (long) jdbcTemplate.queryForList("select count(1) as total from user").get(0).get("total");
    }
}
