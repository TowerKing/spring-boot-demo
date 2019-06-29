package io.github.towerking.springbootmybatis;

import io.github.towerking.springbootmybatis.mapper.UserMapper;
import io.github.towerking.springbootmybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("io.github.towerking.springbootmybatis.mapper")
public class SpringBootMybatisApplication implements ApplicationRunner {

    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = User.builder().name("Hello").build();
        int count = userMapper.save(user);
        log.info("Save {} User {}", count, user);

        user = User.builder().name("world").build();
        count = userMapper.save(user);
        log.info("Save {} User {}", count, user);

        log.info("Find User {}", userMapper.findById(1));
    }
}
