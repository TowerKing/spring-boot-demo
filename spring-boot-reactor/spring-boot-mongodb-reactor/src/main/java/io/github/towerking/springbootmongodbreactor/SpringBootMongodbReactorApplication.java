package io.github.towerking.springbootmongodbreactor;

import io.github.towerking.springbootmongodbreactor.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@Slf4j
public class SpringBootMongodbReactorApplication implements ApplicationRunner {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    private CountDownLatch countDownLatch = new CountDownLatch(2);


    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbReactorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // attention multi threads execute orders
        // startFromInsertion(() -> log.info("Runnable"));

        startFromInsertion(() -> {
            log.info("Runnable");
            grow();
        });

        log.info("after starting... ...");

        // grow();

        countDownLatch.await();
    }

    private void startFromInsertion(Runnable runnable) {
        mongoTemplate.insertAll(mockUser())
                .publishOn(Schedulers.elastic())
                .doOnNext(user -> log.info("Next: {}", user))
                .doOnComplete(runnable)     // attention
                .doFinally(s -> {
                    countDownLatch.countDown();
                    log.info("Finally 1, {}", s);
                })
                .count()
                .subscribe(user -> log.info("Insert {} records", user));
    }

    private void grow() {
        mongoTemplate.updateMulti(Query.query(Criteria.where("age").is(18)),
                    new Update().inc("age", 1)
                .currentDate("updateTime"), User.class)
                .doFinally(s -> {
                    countDownLatch.countDown();
                    log.info("Finally 2, {}", s);
                })
                .subscribe(r -> log.info("Result is {}", r));
    }


    private List<User> mockUser() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            User user = User.builder()
                    .name("Hello a" + i)
                    .age(18 + i)
                    .build();
            users.add(user);
        }

        return users;
    }

}
