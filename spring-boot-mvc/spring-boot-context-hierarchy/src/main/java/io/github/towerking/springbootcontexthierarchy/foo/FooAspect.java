package io.github.towerking.springbootcontexthierarchy.foo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class FooAspect {

    @AfterReturning("bean(helloBean*)")
    public void printAfter() {
        log.info("after hello()");
    }

}
