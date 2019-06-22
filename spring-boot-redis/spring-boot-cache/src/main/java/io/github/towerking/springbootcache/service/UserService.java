package io.github.towerking.springbootcache.service;


import io.github.towerking.springbootcache.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames = "spring-boot-demo-cache-user")
@Slf4j
public class UserService {

    @Cacheable
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User(i, "name0" + i, 20 + i);
            userList.add(user);
        }
        log.info("Fake read users from databases");
        return userList;
    }

    @CacheEvict
    public void reloadUsers() {

    }
}
