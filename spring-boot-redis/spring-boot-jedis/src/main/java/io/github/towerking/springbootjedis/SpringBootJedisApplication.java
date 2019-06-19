package io.github.towerking.springbootjedis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@SpringBootApplication
@Slf4j
public class SpringBootJedisApplication implements ApplicationRunner {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootJedisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(jedisPoolConfig.toString());

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set("test", "test");
            jedis.expire("test", 30000);
            log.info("test value {}", jedis.get("test"));
        }
    }
}
