package io.github.towerking.springbootredisreactor;

import io.github.towerking.springbootredisreactor.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@Slf4j
public class SpringBootRedisReactorApplication implements ApplicationRunner {

    private static final String KEY = "spring:boot:redis:reactor:user:cache";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ReactiveStringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisReactorApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ReactiveHashOperations<String, String, String> hashOperations = redisTemplate.opsForHash();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        List<User> list = jdbcTemplate.query("select * from t_user", (rs, i) ->
                        User.builder()
                                .id(rs.getInt("id"))
                                .name(rs.getString("name"))
                                .createTime(rs.getDate("create_time"))
                                .updateTime(rs.getDate("update_time"))
                                .build()
                );


        Flux.fromIterable(list)
                .publishOn(Schedulers.single())
                .doOnComplete(() -> log.info("list ok"))
                .flatMap(u -> {
                    log.info("try to put {}, {}", u.getName(), u.toString());
                    return hashOperations.put(KEY, u.getName(), u.toString());
                })
                .doOnComplete(() -> log.info("set ok"))
                .concatWith(redisTemplate.expire(KEY, Duration.ofMinutes(1)))
                .doOnComplete(() -> log.info("expire ok"))
                .onErrorResume(e -> {
                    log.error("exception {}", e.getMessage());
                    return Mono.just(false);
                })
                .subscribe(b -> log.info("Boolean {}", b),
                        e -> log.error("Exception: {}", e.getMessage()),
                        () -> countDownLatch.countDown());
        log.info("Waiting..... .....");
        countDownLatch.await();
    }
}
