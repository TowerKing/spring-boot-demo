package io.github.towerking.springboothelloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class SpringBootHelloworldApplication implements ApplicationRunner {

    public SpringBootHelloworldApplication() {
        log.info("Initializing SpringBootHelloworldApplication");
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello World, ha ha ha, Welcome !");
    }
}
