package io.github.towerking.springbootmongodbreactor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class SpringBootMongodbReactorApplication implements ApplicationRunner {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    private CountDownLatch countDownLatch = new CountDownLatch(2);


    public static void main(String[] args) {
        SpringApplication.run(SpringBootMongodbReactorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
