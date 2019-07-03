package io.github.towerking.springbootr2dbcreactor;

import io.github.towerking.springbootr2dbcreactor.model.User;
import io.github.towerking.springbootr2dbcreactor.repository.UserRepository;
import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@Slf4j
@EnableR2dbcRepositories
public class SpringBootR2dbcReactorApplication extends AbstractR2dbcConfiguration implements ApplicationRunner {

    @Autowired
    private UserRepository repository;

    @Autowired
    private DatabaseClient client;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootR2dbcReactorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        // demoByClient(countDownLatch);
        demoByRepository(countDownLatch);
        countDownLatch.await();
    }

    private void demoByRepository(CountDownLatch countDownLatch) {
        repository.findAllById(Flux.just(1, 2))
                .map(u -> u.getName() + "- age: " + u.getAge().toString())
                .doFinally(s -> countDownLatch.countDown())
                .subscribe(u -> log.info("Find {}", u));

        repository.findByName("hello1")
                .doFinally(s -> countDownLatch.countDown())
                .subscribe(u -> log.info("Find {}", u));
    }

    private void demoByClient(CountDownLatch countDownLatch) {
        client.execute()
                .sql("select * from t_user")
                .as(User.class)
                .fetch()
                .first()
                .doFinally(s -> countDownLatch.countDown())
                .subscribeOn(Schedulers.elastic())
                .subscribe(user -> log.info("Fetch execute {}", user));

        client.select()
                .from("t_user")
                .orderBy(Sort.by(Sort.Direction.DESC, "id"))
                .page(PageRequest.of(0, 3))
                .as(User.class)
                .fetch()
                .all()
                .doFinally(s -> countDownLatch.countDown())
                .subscribeOn(Schedulers.elastic())
                .subscribe(u -> log.info("Fetch select {}", u));

        log.info("after starting... ...");
    }

    @Bean
    @Override
    public ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory((
                H2ConnectionConfiguration.builder()
                        .inMemory("testdb")
                        .username("sa")
                        .build()
                ));
    }
}
