package io.github.towerking.springboothelloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class HelloWorldApplicationRunner implements ApplicationRunner {

    public HelloWorldApplicationRunner() {
        log.info("Initializing HelloWorldApplicationRunner");
    }

    public void run(ApplicationArguments args) throws Exception {
        log.info("Hello World, ha ha ha, Welcome !");
    }
}
