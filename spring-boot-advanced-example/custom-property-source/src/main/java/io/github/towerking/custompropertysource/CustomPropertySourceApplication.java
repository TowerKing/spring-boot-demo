package io.github.towerking.custompropertysource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CustomPropertySourceApplication implements ApplicationRunner {

    @Value("${custom.hello}")
    private String hello;

    public static void main(String[] args) {
        SpringApplication.run(CustomPropertySourceApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info(hello);
    }
}
