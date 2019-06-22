package io.github.towerking.springbootcache;

import io.github.towerking.springbootcache.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching(proxyTargetClass = true)
@Slf4j
public class SpringBootCacheApplication implements ApplicationRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCacheApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Count: {}", userService.getAllUsers().size());

        log.info("---- Reading from cache begin ----");
        for (int i = 0; i < 5; i++) {
            log.info("NO {}", i + 1);
            userService.getAllUsers().forEach(u -> log.info("User {}", u));

        }
        log.info("---- Reading from cache end   ----");

        userService.reloadUsers();

        log.info("Reading after refresh.");
        userService.getAllUsers().forEach(u -> log.info("User {}", u));

    }
}
