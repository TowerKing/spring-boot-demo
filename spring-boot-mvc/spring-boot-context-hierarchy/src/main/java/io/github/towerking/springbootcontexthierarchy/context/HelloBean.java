package io.github.towerking.springbootcontexthierarchy.context;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class HelloBean {

    private String context;

    public void hello() {
        log.info("hello " + context);
    }

}
