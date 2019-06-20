package io.github.towerking.springbootredisrepository;

import io.github.towerking.springbootredisrepository.model.UserCache;
import io.github.towerking.springbootredisrepository.repository.UserCacheRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.util.Optional;

@SpringBootApplication
@Slf4j
@EnableRedisRepositories
public class SpringBootRedisRepositoryApplication implements ApplicationRunner {

    @Autowired
    private UserCacheRepository cacheRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisRepositoryApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String name = "Hello";
        Optional<UserCache> cached = cacheRepository.findUserCacheByName(name);
        if (cached.isPresent()) {
            UserCache userCache = cached.get();
            log.info("User cache {}", userCache);
        } else {
            UserCache userCache = UserCache.builder()
                    .id(1)
                    .name(name)
                    .gender("M")
                    .age(18)
                    .build();

            log.info("Save user {} to cache", userCache);
            cacheRepository.save(userCache);
        }

    }
}
