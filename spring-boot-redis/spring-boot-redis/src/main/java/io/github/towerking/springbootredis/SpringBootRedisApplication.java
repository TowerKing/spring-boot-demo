package io.github.towerking.springbootredis;

import io.github.towerking.springbootredis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Slf4j
public class SpringBootRedisApplication implements ApplicationRunner {

    private static final String CACHE = "spring-boot-redis-user";

    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String userName = "tower king";

        HashOperations<String, String, User> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, userName)) {
            log.info("Get User {} from redis", userName);
        }

        User user = User.builder()
                .id(1)
                .name(userName)
                .gender("male")
                .age(18)
                .build();

        hashOperations.put(CACHE, userName, user);
        redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);

    }
}
